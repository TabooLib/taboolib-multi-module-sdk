import io.izzel.taboolib.gradle.UNIVERSAL

dependencies {
    // 如果不需要跨平台，可以在此处引入 Bukkit 核心
    // compileOnly("ink.ptms.core:v11903:11903:mapped")
    // compileOnly("ink.ptms.core:v11903:11903:universal")
}

taboolib {
    // 子模块
    subproject = true
    env {
        install(UNIVERSAL)
    }
}