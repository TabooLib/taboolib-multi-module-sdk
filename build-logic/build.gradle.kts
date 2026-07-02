plugins {
    `kotlin-dsl`
}

group = "io.github.username.project.buildlogic"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.10")
    implementation("org.jetbrains.kotlin:kotlin-serialization:2.1.0")
    implementation("io.izzel.taboolib:io.izzel.taboolib.gradle.plugin:2.0.31")
}

gradlePlugin {
    plugins {
        register("taboolibLibrary") {
            id = "conventions.taboolib-library"
            implementationClass = "io.github.username.project.conventions.TaboolibLibraryConventionPlugin"
        }
        register("taboolibRuntimeBukkit") {
            id = "conventions.taboolib-runtime-bukkit"
            implementationClass = "io.github.username.project.conventions.TaboolibRuntimeBukkitConventionPlugin"
        }
    }
}