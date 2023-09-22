package template.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = Integer.parseInt(libs.findVersion("compileSdk").get().toString())
        defaultConfig {
            minSdk = Integer.parseInt(libs.findVersion("minSdk").get().toString())
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        // Use withType to workaround https://youtrack.jetbrains.com/issue/KT-55947
        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                // Set JVM target to 17
                jvmTarget = JavaVersion.VERSION_17.toString()
                // Treat all Kotlin warnings as errors (disabled by default)
                // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
                val warningsAsErrors: String? by project
                allWarningsAsErrors = warningsAsErrors.toBoolean()
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-opt-in=kotlin.RequiresOptIn",
                    // Enable experimental coroutines APIs, including Flow
                    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-opt-in=kotlinx.coroutines.FlowPreview",
                    "-opt-in=kotlin.Experimental",
                )
            }
        }

        testOptions {
            unitTests {
                isIncludeAndroidResources = true
                isReturnDefaultValues = true
            }
        }

        packaging {
            resources {
                resources.excludes.add("META-INF/*")
                resources.merges.add("META-INF/*.version")
            }
        }
    }
}
