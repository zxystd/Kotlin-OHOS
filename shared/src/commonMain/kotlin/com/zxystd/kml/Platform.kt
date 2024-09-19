package com.zxystd.kml

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform