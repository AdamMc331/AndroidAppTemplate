import com.android.build.gradle.internal.tasks.factory.dependsOn
import java.util.Locale

plugins {
    libs.plugins.apply {
        alias(android.application) apply false
        alias(kotlin.parcelize) apply false
        alias(android.library) apply false
        alias(kotlin.android) apply false
        alias(hilt.android) apply false
        alias(kapt) apply false
        alias(detekt) apply false
        alias(kotliner) apply false
        alias(ksp) apply false
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}


tasks.register("copyGitHooks", Exec::class.java) {
    description = "Copies the git hooks from /git-hooks to the .git folder."
    group = "git hooks"

    workingDir = File("$projectDir")

    val command = """
       .\git-hooks\setup.sh
    """.trimIndent()

    if (System.getProperty("os.name").lowercase(Locale.ROOT).contains("windows")) {
        commandLine("cmd", "/c", command)
    } else {
        commandLine("sh", "/c", command)
    }
}



tasks.register("installGitHooks", Exec::class.java) {
    description = "Installs the pre-commit git hooks from /git-hooks."
    group = "git hooks"
    workingDir = rootDir

    if (System.getProperty("os.name").lowercase(Locale.ROOT).contains("windows")) {
        logger.info("Windows")
        commandLine("cmd", "-R", "+x", ".git/hooks/")
    } else {
        logger.info("Windows")
        commandLine("sh", "-R", "+x", ".git/hooks/")
    }
    dependsOn("copyGitHooks")
    doLast {
        logger.info("Git hook installed successfully.")
    }
}

afterEvaluate {
    // We install the hook at the first occasion
    tasks.named("clean").dependsOn(":installGitHooks")
}
