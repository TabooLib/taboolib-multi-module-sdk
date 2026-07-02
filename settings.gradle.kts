pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}

rootProject.name = "ExampleProject"

include("core")
include("runtime-bukkit")