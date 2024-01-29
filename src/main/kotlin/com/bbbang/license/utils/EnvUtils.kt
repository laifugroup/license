package com.bbbang.license.utils

import com.bbbang.license.domain.LicenseInfo
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import oshi.SystemInfo

class EnvUtils {
    companion object{
        fun getEnv(): LicenseInfo {
            val systemInfo = SystemInfo()
            val hal = systemInfo.hardware
            val cupId = hal.processor.processorIdentifier.processorID
            val mainBoardSerial = hal.computerSystem.hardwareUUID
            val networkIF=hal.networkIFs
            val macList=networkIF.filterNotNull().joinToString(","){
                it.macaddr
            }
            val ipv4List=networkIF.filterNotNull().joinToString(","){
                it.iPv4addr.filterNotNull().joinToString("-")
            }
            val licenseInfo=LicenseInfo(cupId,ipv4List,macList,mainBoardSerial)
            return licenseInfo
        }

        fun getEnvFormat():String{
            val licenseInfo=getEnv()
            val objectMapper= ObjectMapper()
            val json = objectMapper.writer(DefaultPrettyPrinter()).writeValueAsString(licenseInfo)
            return json
        }
    }
}