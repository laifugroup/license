package com.bbbang.license.cmd

import com.bbbang.parent.keymgr.LicenseManager
import global.namespace.truelicense.api.LicenseManagementException
import io.micronaut.http.HttpStatus
import picocli.CommandLine
import java.util.concurrent.Callable

@CommandLine.Command(name = "load",description=["加载证书-加载证书信息"])
 class LoadCmd : Callable<Any> {
    @Throws(Exception::class)
    override fun call(): Any {
        try {
            val loadCert= LicenseManager.standard.load()
            println("\n")
            println("授权用户："+loadCert.info)
            println("过期时间："+loadCert.notAfter)
            println("\n")
            return loadCert
        }catch (e: LicenseManagementException){
            val loadFailure="证书加载错误,请联系管理员"
            println(loadFailure)
            return  loadFailure
        }
    }
}
