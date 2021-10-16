# Static Analysis

This project leverages static analysis to ensure that the codebase meets certain standards that can be verified through automation. Two of these libraries are Detekt and Ktlint.

## Detekt

[Detekt](https://github.com/detekt/detekt) is a static analysis tool that checks for code smells. Examples include magic numbers, complicated conditionals, long methods, long parameter lists, and so much more. It is highly configurable, and if you choose to turn off any checks or customize thresholds you can do so in the [config file](/config/detekt/detekt.yml).

To run a detekt validation, use the following Gradle command:

```
./gradlew detekt
```

## Ktlint

[Ktlint](https://github.com/pinterest/ktlint) is a static analysis tool from Pinterest that prevents bike shedding when it comes to code formatting. It also comes with a Gradle task to automatically format your entire codebase, if it can. The benefit of a tool like this is to ensure everyone on the team will have code formatted the same way, and there's no debating around white spaces, indentation, imports, etc. 

We use the [JLLeitschuh](https://github.com/jlleitschuh/ktlint-gradle) Ktlint Gradle plugin in this project. You can find the setup in [this Gradle file](/buildscripts/ktlint.gradle).

The following Gradle commands can be helpful:

```
// Will format the codebase
./gradlew ktlintFormat

// Will check if everything is formatted correctly
./gradlew ktlintCheck
```