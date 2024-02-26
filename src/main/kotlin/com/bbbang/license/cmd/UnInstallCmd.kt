package com.bbbang.license.cmd

import com.bbbang.parent.keymgr.LicenseManager
import global.namespace.`fun`.io.bios.BIOS
import io.micronaut.http.HttpStatus
import picocli.CommandLine
import java.io.FileInputStream
import java.util.concurrent.Callable

@CommandLine.Command(name = "uninstall",description=["卸载指令"])
 class UnInstallCmd : Callable<Any> {

    @Throws(Exception::class)
    override fun call(): Any {
        try {
            LicenseManager.standard.uninstall()
        }catch (e:Exception){
            //no-null
        }finally {
            val uninstallSuccess="----卸载成功----"
            println("\n")
            println(uninstallSuccess)
            println("\n")
        }
        return "----卸载成功----"
    }
}
