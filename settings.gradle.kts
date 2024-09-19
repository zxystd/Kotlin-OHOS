enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        maven {
            url = uri("/Users/zxy/Downloads/kotlin/build/repo")
        }
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("/Users/zxy/Downloads/kotlin/build/repo")
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "kml"
include(":shared")