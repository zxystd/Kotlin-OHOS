package com.zxystd.kml

import kotlinx.cinterop.ExperimentalForeignApi
import platform.log.LOG_APP
import platform.log.LOG_INFO
import platform.log.OH_LOG_Print
import platform.posix.pthread_self
import platform.posix.pthread_t
import kotlin.experimental.ExperimentalNativeApi

@kotlin.experimental.ExperimentalNativeApi
class HarmonyOSPlatform: Platform {
    override val name: String
        get() {
            test()
            return "HarmonyOS Beta"
        }
}

@OptIn(ExperimentalNativeApi::class)
actual fun getPlatform(): Platform = HarmonyOSPlatform()

@OptIn(ExperimentalForeignApi::class)
fun test() {
    val pthreadSelf: pthread_t? = pthread_self()
    OH_LOG_Print(LOG_APP, LOG_INFO,
        0u, "hello", "%{public}d %{public}s\n", pthreadSelf, "HarmonyOS Beta")
}