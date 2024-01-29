package com.bbbang.license.cmd

import com.bbbang.license.utils.EnvUtils
import picocli.CommandLine
import java.util.concurrent.Callable

@CommandLine.Command(name = "env",description=["运行环境参数"])
 class EnvironmentCmd : Callable<Any> {
    @Throws(Exception::class)
    override fun call(): Any {
        val envJson= EnvUtils.getEnvFormat()
        println("\n")
        println(envJson)
        println("\n---- 请把以上JSON提供给服务商 ----")
        return envJson
    }
}
