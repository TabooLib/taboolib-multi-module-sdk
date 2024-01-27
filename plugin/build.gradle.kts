@file:Suppress("PropertyName", "SpellCheckingInspection")

import io.izzel.taboolib.gradle.BUKKIT_ALL
import io.izzel.taboolib.gradle.UNIVERSAL

taboolib {
    description {
        name(rootProject.name)
        contributors {
            name("Bkm016")
        }
    }
    env {
        // 此处写入所有 subprojects 使用到的 TabooLib 模块
        install(UNIVERSAL, BUKKIT_ALL)
    }
}

tasks {
    jar {
        // 构件名
        archiveFileName.set("${rootProject.name}-${archiveFileName.get().substringAfter('-')}")
        // 打包子项目源代码
        rootProject.subprojects.forEach { from(it.sourceSets["main"].output) }
    }
    sourcesJar {
        // 构件名
        archiveFileName.set("${rootProject.name}-${archiveFileName.get().substringAfter('-')}")
        // 打包子项目源代码
        rootProject.subprojects.forEach { from(it.sourceSets["main"].allSource) }
    }
}