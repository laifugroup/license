package com.bbbang.license.cmd

import com.bbbang.parent.keymgr.LicenseManager
import global.namespace.truelicense.api.LicenseManagementException
import io.micronaut.http.HttpStatus
import picocli.CommandLine
import java.util.concurrent.Callable

@CommandLine.Command(name = "verify",description=["验证证书-验证是否成功"])
 class VerifyCmd : Callable<Any> {
    @Throws(Exception::class)
    override fun call(): Any {
        try {
            LicenseManager.standard.verify()
        }catch (e: LicenseManagementException){
            val  verifyFailure="\n----证书校验失败,请联系管理员----\n"
            println(verifyFailure)
            return verifyFailure
        }
        val verifyPass="\n----证书校验成功----\n"
        println(verifyPass)
        return verifyPass
    }
}
