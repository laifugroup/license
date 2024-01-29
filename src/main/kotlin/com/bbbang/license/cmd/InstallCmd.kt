package com.bbbang.license.cmd

import com.bbbang.parent.keymgr.LicenseManager
import global.namespace.`fun`.io.bios.BIOS
import io.micronaut.http.HttpStatus
import picocli.CommandLine
import java.io.FileInputStream
import java.util.concurrent.Callable

@CommandLine.Command(name = "install",description=["安装指令-需要路径参数"])
 class InstallCmd : Callable<Any> {

    @CommandLine.Option(names = ["-path", "--path"], description = ["授权文件路径"])
    private var path : String = "./license.lic"

    @Throws(Exception::class)
    override fun call(): Any {
        try {
            LicenseManager.standard.uninstall()
        }catch (e:Exception){
            //no-null
        }finally {
            try {
                val manager = LicenseManager.standard
                val inputStream= FileInputStream(path)
                val bytes=inputStream.readAllBytes()
                //1. 使用inputStream流式版本安装不成功
                //val source = BIOS.stream(inputStream)
                //2.使用内存版本可以安装成功
                val source = BIOS.memory(bytes.size)
                source.content(bytes)
                manager.install(source)
                val installSuccess="----证书安装成功----"
                println("\n")
                println(installSuccess)
                println("\n")
                return  installSuccess
            }catch (e:Exception){
                val installFailure="----证书安装失败----"
                println("\n")
                println(installFailure)
                println(e.message)
                println("\n")
            }
        }
        return "----安装无效----"
    }
}
