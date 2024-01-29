package com.bbbang.license.utils

import java.time.LocalDate
import java.util.regex.Pattern

class PasswdUtils {
    companion object{

        /**
         * 双重字符
         */
        private const val ENCODE_PASSWORD = "\$2a\$10\$JcUYpSCbzd0jsHVln3AuBOcCq8/0js4VVuqr9NW./rkH6Hc41wzNq"
        /**
         * 密码规则  [1]\s[29]   月份 密码 日
         *
         */
        fun verifyPassword(password:String?):Boolean{
            password?.let {
                val monthOfYear=LocalDate.now().monthValue.toString()
                val daysOfMonth=LocalDate.now().dayOfMonth.toString()
                if (!it.startsWith(monthOfYear)){
                    return false
                }
                if (!it.endsWith(daysOfMonth)){
                    return false
                }
                val realPasswd=it.replaceFirst(monthOfYear,"")
                    .reversed().replaceFirst(daysOfMonth.reversed(),"")
                    .reversed()
                return BCryptPasswordEncoder.matches(realPasswd,ENCODE_PASSWORD)
            }
            return false
        }
    }


}

fun main() {
     val passwd="1year2024fafafa29"
  val pass=  PasswdUtils.verifyPassword(passwd)
    println(pass)
}