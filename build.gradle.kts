// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath(libs.detekt.gradle.plugin)
        classpath(libs.gradle)
        classpath(libs.gradle.versions.plugin)
        classpath(libs.kotlin.gradle.plugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }

    // Currently overriding the ktlint version to 1.0.1, can remove in the future when kotlinter
    // is updated: https://github.com/jeremymailen/kotlinter-gradle#custom-ktlint-version
    configurations.classpath {
        resolutionStrategy {
            force(
                "com.pinterest.ktlint:ktlint-rule-engine:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-rule-engine-core:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-core:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-checkstyle:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-json:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-html:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-plain:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-sarif:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-ruleset-standard:${libs.versions.ktlint.get()}",
            )
        }
    }
}

plugins {
    id("app.cash.paparazzi").version(libs.versions.paparazzi).apply(false)
    id("com.google.dagger.hilt.android").version(libs.versions.hilt).apply(false)
    id("com.google.devtools.ksp").version(libs.versions.ksp).apply(false)
    id("com.squareup.sort-dependencies").version(libs.versions.sortDependencies).apply(false)
    id("io.gitlab.arturbosch.detekt").version(libs.versions.detektGradlePlugin)
    id("org.jmailen.kotlinter").version(libs.versions.kotlinter).apply(false)
}

apply(from = "buildscripts/githooks.gradle")
apply(from = "buildscripts/setup.gradle")
apply(from = "buildscripts/versionsplugin.gradle")

subprojects {
    apply(from = "../buildscripts/detekt.gradle")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

afterEvaluate {
    // We install the hook at the first occasion
    tasks.named("clean") {
        dependsOn(":installGitHooks")
    }
}

tasks {
    /**
     * The detektAll tasks enables parallel usage for detekt so if this project
     * expands to multi module support, detekt can continue to run quickly.
     *
     * https://proandroiddev.com/how-to-use-detekt-in-a-multi-module-android-project-6781937fbef2
     */
    @Suppress("UnusedPrivateMember")
    val detektAll by registering(io.gitlab.arturbosch.detekt.Detekt::class) {
        parallel = true
        setSource(files(projectDir))
        include("**/*.kt")
        include("**/*.kts")
        exclude("**/resources/**")
        exclude("**/build/**")
        config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
        buildUponDefaultConfig = false
    }
}
