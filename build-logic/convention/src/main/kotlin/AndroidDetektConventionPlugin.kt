import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import template.buildlogic.configureDetekt
import template.buildlogic.libs

class AndroidDetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(libs.findLibrary("detekt-gradlePlugin").get().get().group)
            val extension = extensions.getByType<DetektExtension>()
            configureDetekt(extension)
        }
    }
}
