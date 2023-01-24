// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("kotlinVersion", "1.7.10")

        // https://github.com/JLLeitschuh/ktlint-gradle/releases
        set("ktlintPluginVersion", "11.0.0")

        // https://github.com/detekt/detekt/releases
        set("detektVersion", "1.21.0")

        // https://github.com/ben-manes/gradle-versions-plugin
        set("versionsPluginVersion", "0.42.0")
    }

    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${rootProject.extra.get("kotlinVersion")}")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:${rootProject.extra.get("ktlintPluginVersion")}")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${rootProject.extra.get("detektVersion")}")
        classpath("com.github.ben-manes:gradle-versions-plugin:${rootProject.extra.get("versionsPluginVersion")}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

apply(from = "buildscripts/githooks.gradle")
apply(from = "buildscripts/setup.gradle")

subprojects {
    apply(from = "../buildscripts/ktlint.gradle")
    apply(from = "../buildscripts/detekt.gradle")
    apply(from = "../buildscripts/versionsplugin.gradle")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

afterEvaluate {
    // We install the hook at the first occasion
    tasks.named("clean") {
        dependsOn(":installGitHooks")
    }
}
