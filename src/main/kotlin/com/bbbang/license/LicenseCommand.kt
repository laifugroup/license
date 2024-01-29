package com.bbbang.license

import com.bbbang.license.cmd.*
import io.micronaut.configuration.picocli.PicocliRunner
import picocli.CommandLine.*


@Command(name = "license", header = [
    "---- CERT LICENSE  SERVICE----",
    ],
    description = ["证书授权服务系统"],
    mixinStandardHelpOptions = true,
    version = ["1.0.0"]
    ,
    subcommands = [EnvironmentCmd::class, GeneratorCmd::class, InstallCmd::class, VerifyCmd::class, LoadCmd::class]
)
class LicenseCommand : Runnable {


    override fun run() {
        // business logic here
        println("----本应用是[证书管理]命令行工具,请使用[-h]查看指令----")
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            PicocliRunner.run(LicenseCommand::class.java, *args)
        }
    }
}









