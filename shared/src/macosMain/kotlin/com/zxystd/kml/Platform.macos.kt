package com.zxystd.kml

class MACOSPlatform: Platform {
    override val name: String = "macOS"
}

actual fun getPlatform(): Platform = MACOSPlatform()