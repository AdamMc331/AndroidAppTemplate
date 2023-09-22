package template

import org.gradle.api.Plugin
import org.gradle.api.Project
import template.buildlogic.configureDependencyChecker

class AndroidVersionUpdateCheckerConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        pluginManager.apply("com.github.ben-manes.versions")
        configureDependencyChecker()
    }
}
