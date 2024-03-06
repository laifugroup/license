package com.bbbang.license.utils

import com.bbbang.license.domain.LicenseInfo
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import oshi.SystemInfo

class EnvUtils {
    companion object{
        fun getEnv(): LicenseInfo {
            val si = SystemInfo()
            val hal = si.hardware
            val cupId = hal.processor.processorIdentifier.processorID
            val mainBoardSerial = hal.computerSystem.hardwareUUID

            val networkIF = hal.networkIFs
            val macList = networkIF.filterNotNull().joinToString(",") {
                it.macaddr
            }
            val ipv4List = networkIF.filterNotNull().joinToString(",") {
                it.iPv4addr.filterNotNull().joinToString("-")
            }
            return LicenseInfo(cpu=cupId, ip=ipv4List, mac=macList, mainBoard =mainBoardSerial)
        }

        fun getEnvFormat(): String {
            val licenseInfo = getEnv()
            val objectMapper = ObjectMapper()
            return objectMapper.writer(DefaultPrettyPrinter()).writeValueAsString(licenseInfo)
        }
    }
}