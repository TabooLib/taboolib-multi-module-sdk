package io.github.username.project.conventions

import io.izzel.taboolib.gradle.Basic
import io.izzel.taboolib.gradle.Bukkit
import io.izzel.taboolib.gradle.BukkitNMSEntityAI
import io.izzel.taboolib.gradle.BukkitNMSUtil
import io.izzel.taboolib.gradle.BukkitNavigation
import io.izzel.taboolib.gradle.BukkitUI
import io.izzel.taboolib.gradle.BukkitUtil
import io.izzel.taboolib.gradle.CommandHelper
import io.izzel.taboolib.gradle.Kether
import io.izzel.taboolib.gradle.MinecraftChat
import io.izzel.taboolib.gradle.MinecraftEffect
import io.izzel.taboolib.gradle.TabooLibExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * TabooLib 子模块约定：Kotlin、序列化、TabooLib env 与 Java 8 编译选项。
 *
 * @author sky
 */
class TaboolibLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("java")
            pluginManager.apply("org.jetbrains.kotlin.jvm")
            pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")
            pluginManager.apply("io.izzel.taboolib")

            repositories.mavenCentral()

            configure<TabooLibExtension> {
                description {
                    name(rootProject.name)
                }
                env {
                    install(Basic, Bukkit, BukkitUtil, BukkitNMSUtil, BukkitNMSEntityAI, BukkitUI, Kether)
                    install(MinecraftChat, MinecraftEffect, BukkitNavigation)
                    install(CommandHelper)
                }
                version { taboolib = "6.3.0-a1d3953" }
            }

            dependencies {
                add("compileOnly", kotlin("stdlib"))
            }

            extensions.configure<JavaPluginExtension> {
                withSourcesJar()
                sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
                targetCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
            }

            tasks.withType<JavaCompile>().configureEach {
                options.encoding = "UTF-8"
            }

            tasks.withType<KotlinCompile>().configureEach {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                    freeCompilerArgs.add("-Xjvm-default=all")
                }
            }
        }
    }
}