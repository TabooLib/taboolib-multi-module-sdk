package io.github.username.project.conventions

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.getByName
import org.gradle.kotlin.dsl.project

/**
 * Bukkit 运行时模块约定：在库模块约定之上，将 `core` 产物并入最终插件 jar。
 *
 * @author sky
 */
class TaboolibRuntimeBukkitConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("conventions.taboolib-library")

            dependencies {
                add("implementation", project(":core"))
            }

            val coreProject = rootProject.project(":core")
            val coreSourceSets = coreProject.extensions.getByType<SourceSetContainer>()
            val coreMain = coreSourceSets.getByName("main")

            tasks.named<Jar>("jar").configure {
                archiveBaseName.set(rootProject.name)
                dependsOn(coreProject.tasks.named("jar"))
                from(coreMain.output)
            }

            tasks.named<Jar>("sourcesJar").configure {
                archiveBaseName.set(rootProject.name)
                dependsOn(coreProject.tasks.named("classes"))
                from(coreMain.allSource)
            }
        }
    }
}