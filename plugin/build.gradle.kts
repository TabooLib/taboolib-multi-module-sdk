import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

dependencies {
    implementation(project(":project:common"))
    implementation(project(":project:implementation-bukkit"))
}

tasks {
    withType<ShadowJar> {
        archiveClassifier.set("")
    }
    build {
        dependsOn(shadowJar)
    }
}