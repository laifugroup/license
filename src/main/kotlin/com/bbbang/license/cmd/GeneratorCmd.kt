package com.bbbang.license.cmd

import com.bbbang.license.domain.LicenseInfo
import com.bbbang.license.utils.EnvUtils
import com.bbbang.license.utils.PasswdUtils
import com.bbbang.parent.keygen.LicenseManager
import com.bbbang.parent.keymgr.LicenseConstants
import global.namespace.`fun`.io.api.Sink
import global.namespace.`fun`.io.bios.BIOS.file
import global.namespace.truelicense.api.LicenseManagementException
import global.namespace.truelicense.api.VendorLicenseManager
import picocli.CommandLine
import java.util.concurrent.Callable
import javax.security.auth.x500.X500Principal


@CommandLine.Command(name = "generator",description=["生成证书-需要密码参数"])
 class GeneratorCmd : Callable<Any> {

//这里的密码定义后，需要在命令行中设置密码,不是我需要的
//    @Parameters(
//        description = ["需要密码,示例指令：[run.jar passwd123]"],
//        split = ",", //仅仅用于列表参数
//        paramLabel = "password"
//    )
//    private var args: List<String>?=null

    @CommandLine.Option(names = ["-days", "--days"], description = ["证书有效期[天]"])
    private var licenseDays : Int = 1

    @CommandLine.Option(names = ["-licenseTo", "--licenseTo"], description = ["授权对象[user]"])
    private var licenseToUser : String = "user"

    @Throws(Exception::class)
    override fun call(): Any {

        if (licenseDays<=0){
            val daysVerify="[days]天数必须大于0"
            println(daysVerify)
            return daysVerify
        }
        //密码验证
        val console = System.console()
        val passwordChars = console.readPassword("请输入密码: ")
        val password = String(passwordChars)

        if (!PasswdUtils.verifyPassword(password)){
            val passwordVerify="[passwd]操作密码错误,请重新输入。"
            println(passwordVerify)
            return passwordVerify
        }
        val licenseInfo= EnvUtils.getEnv().also {
            it.days=licenseDays
            it.licenseTo=licenseToUser
        }
        genLicense(licenseInfo)
        return  licenseInfo
    }

    private fun genLicense(licenseInfo: LicenseInfo){
        val name = "CN=${licenseInfo.licenseTo} OU=${licenseInfo.licenseTo} O=${licenseInfo.licenseTo} C=US"
        val x500Principal = X500Principal(name)

        val manager: VendorLicenseManager = LicenseManager.standard
        val licenseInput = manager.context().licenseFactory().license()
        licenseInfo.days?.let { licenseInput.setTerm(it) }
        licenseInput.holder = x500Principal
        licenseInput.info = "仅授权${licenseInfo.licenseTo}使用"

        val extra = mapOf(
            LicenseConstants.ipAddress to licenseInfo.ip,
            LicenseConstants.macAddress to licenseInfo.mac,
            LicenseConstants.cpuSerial to licenseInfo.cpu,
            LicenseConstants.mainBoardSerial to licenseInfo.mainBoard,
            )
        licenseInput.extra = extra
        val licenseFilePath="./license.lic"
        val sink: Sink = file(licenseFilePath)
        try {
            manager.generateKeyFrom(licenseInput).saveTo(sink).license()
            println("\n")
            println("----证书创建成功----")
            println("path: $licenseFilePath")
            println("\n")
        } catch (e: LicenseManagementException) {
            println("----证书创建失败----")
        }

    }
}