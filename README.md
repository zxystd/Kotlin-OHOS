# Kotlin-OHOS
Kotlin native support for OpenHarmony OS and HarmonyOS

HarmonyOS Beta1刚出的时候，抽了一点时间适配了HarmonyOS的native target，没什么时间继续搞太多，抛砖引玉啦。
仓库有点多，源代码还要整理下

### Kotlin
 - [x] Kotlin OHOS KN/Target
   - [x] konan-cli
   - [x] kotlin-compiler
   - [x] kotlin-gradle-plugin

### dependencies
 - [x] stdlib
 - [x] multiplatform
 - [x] kotlinx.coroutines
 - [x] kotlinx.serialization
   - [x] kotlinx-serialization-core
   - [x] kotlinx-serialization-bom
   - [x] kotlinx-serialization-bom
   - [x] kotlinx-serialization-hocon
   - [x] kotlinx-serialization-json
   - [x] kotlinx-serialization-json-io
   - [x] kotlinx-serialization-properties
   - [x] kotlinx-serialization-protobuf
   - [ ] kotlinx-serialization-json-okio
 - [x] kotlinx-datetime
 - [x] kotlinx-atomicfu
 - [x] kotlinx-io
   - [x] kotlinx-io-core
   - [x] kotlinx-io-bytestring
 - [x] binary-compatibility-validator

### platform libs
 - [x] posix
 - [x] log
 - [x] napi
 - [x] zlib
 - [ ] gles
 - [ ] sles
 - [ ] ar
 - [ ] xengine
 - [ ] graphics_game_sdk
 - [ ] RemoteCommunicationKit

### 食用方法
  目前prebuilt的都是基于HarmonyOS Beta1的command-line-tools进行编译的，在beta1上测试了一切正常，其他版本自行尝试。
  宿主机是M系列的电脑，x86 mac和windows/linux暂时没加支持，支持也简单，就是没时间

1. 去官网下载command-line-tools工具，或者已经下了DevEco-Studio也可以，将HarmonyOS的SDK的路径映射到`~/.konan/`目录下，没有这个目录就创建，最终的目录像这样
`/Users/用户名/.konan/HarmonyOS-NEXT-DB1`

2. 解压`kotlin-native-prebuilt-macos-aarch64-2.1.255-SNAPSHOT.tar.gz`文件到`~/.konan/`目录下，最终的目录像这样
`/Users/用户名/.konan/kotlin-native-prebuilt-macos-aarch64-2.1.255-SNAPSHOT`

4. 新建KMP Library工程，在`settings.gradle`仓库添加本地仓库地址，解压repo仓库，比如解压到Download文件夹了，那么就是

```
pluginManagement {
    repositories {
        maven {
            url = uri("/Users/用户名/Downloads/repo")
        }
        mavenLocal()
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("/Users/用户名/Downloads/repo")
        }
        mavenLocal()
        google()
        mavenCentral()
    }
}
```

3. 修改依赖版本，改成本地的snapshot版本
```
[versions]
kotlin = "2.1.255-SNAPSHOT"
kotlinxCoroutinesCore = "1.9.0-RC.2-SNAPSHOT"
kotlinxDatetime = "0.6.1-SNAPSHOT"
kotlinxIo = "0.6.0-SNAPSHOT"
kotlinxSerialization = "1.7.3-SNAPSHOT"
atomicfu = "0.25.0-SNAPSHOT"`

[libraries]
kotlinx-coroutines-core = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-core", version.ref = "kotlinxCoroutinesCore" }
kotlinx-io-core = { module = "org.jetbrains.kotlinx:kotlinx-io-core", version.ref = "kotlinxIo" }
kotlinx-io-bytestring = { module = "org.jetbrains.kotlinx:kotlinx-io-bytestring", version.ref = "kotlinxIo" }
kotlinx-datetime = { module = "org.jetbrains.kotlinx:kotlinx-datetime", version.ref = "kotlinxDatetime" }
kotlinx-serialization-core = { module = "org.jetbrains.kotlinx:kotlinx-serialization-core", version.ref = "kotlinxSerialization" }
kotlinx-serialization-json = { module = "org.jetbrains.kotlinx:kotlinx-serialization-json", version.ref = "kotlinxSerialization" }
kotlin-atomicfu = { module = "org.jetbrains.kotlinx:atomicfu", version.ref = "atomicfu" }
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
```

4. shared模块build.gradle中添加harmonyOS target，如
```
    harmonyOSArm64 {
        binaries.sharedLib {
            baseName = "hellokt"
        }
    }
```

5. 在gradle task里构建，或者命令：
```
./gradlew shared:linkHarmonyOSArm64
```
Build成功的话，在shared/build/bin/harmonyOSArm64目录下会出现so文件和.h文件，可以在arkts中通过npai直接调用

注意，由于kotlin cinterop的要求，宿主机需要安装aarch64的Azul Zulu JDK8，详细可以去kotlin官方仓库查看

### 编译第三方库
  。。。
