package com.bbbang.license.domain

class LicenseInfo(
    val cpu :String?=null,
    val ip :String?=null,
    val mac :String?=null,
    val mainBoard :String?=null,
    var days :Int?=1,
    var licenseTo :String?="user",
    ) {
}