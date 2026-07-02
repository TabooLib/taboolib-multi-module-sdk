plugins {
    id("conventions.taboolib-runtime-bukkit")
}

dependencies {
    compileOnly("ink.ptms.core:v12004:12004:mapped")
    compileOnly("ink.ptms.core:v12004:12004:universal")
}

taboolib {
    subproject = true
}