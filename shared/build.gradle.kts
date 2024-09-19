plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosArm64()
    harmonyOSArm64 {
        binaries.sharedLib {

        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.coroutines.core)
            api(libs.kotlin.atomicfu)
//            api(libs.kotlinx.collections.immutable)
            api(libs.kotlinx.datetime)
            api(libs.kotlinx.io.core)
            api(libs.kotlinx.io.bytestring)
            api(libs.kotlinx.serialization.core)
            api(libs.kotlinx.serialization.json)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0-RC")
        }
        macosMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-macosarm64:1.9.0-RC")
        }
    }

//    iosArm64("ios") {
//        compilations["main"].cinterops {
//
//        }
//    }
}

android {
    namespace = "com.zxystd.kml"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packagingOptions {
        resources.excludes += "DebugProbesKt.bin"
    }
}