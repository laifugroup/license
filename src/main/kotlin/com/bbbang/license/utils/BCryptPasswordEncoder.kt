package com.bbbang.license.utils

import org.mindrot.jbcrypt.BCrypt

class BCryptPasswordEncoder {
    companion object{
        fun encode(rawPassword: CharSequence): String {
            val salt = BCrypt.gensalt()
            return BCrypt.hashpw(rawPassword.toString(), salt)
        }

        fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
            return BCrypt.checkpw(rawPassword.toString(), encodedPassword)
        }
    }
}

fun main() {
    val rawPassword="year2024fafafa"
  val crypt=  BCryptPasswordEncoder.encode(rawPassword)
    println(crypt)
  val pass=  BCryptPasswordEncoder.matches(rawPassword,crypt)
    println(pass)
}