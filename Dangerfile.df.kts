@file:Suppress("MagicNumber", "WildcardImport", "ForbiddenComment")

// Editing this file: https://github.com/danger/kotlin?tab=readme-ov-file#autocomplete-and-syntax-highlighting-in-intellij-idea-or-android-studio
import systems.danger.kotlin.*
import java.io.File

danger(args) {

    onGitHub {
        val additions = pullRequest.additions ?: 0
        val deletions = pullRequest.deletions ?: 0

        message("Thanks @${pullRequest.user.login}!")

        if (pullRequest.body.isNullOrBlank()) {
            fail("Please provide a summary in the Pull Request description.")
        }

        if (additions > 500) {
            warn("Please consider breaking up this pull request.")
        }

        if (issue.labels.isEmpty()) {
            warn("Please add labels to this PR.")
        }

        if (deletions > additions) {
            message("🎉 Code Cleanup!")
        }

        val updatesFile = File("build/dependencyUpdates/report.txt")
        val lines = updatesFile.readLines()

        val headerIndex = lines.indexOfFirst { line ->
            line.contains("The following dependencies have later milestone versions:")
        }

        if (headerIndex >= 0) {
            val message = lines.subList(headerIndex, lines.size).joinToString("\n")
            message(message)
        }
    }
}
