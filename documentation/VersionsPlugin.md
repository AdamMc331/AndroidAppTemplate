# Versions Plugin

This project uses the [Gradle Versions Plugin](https://github.com/ben-manes/gradle-versions-plugin) from Ben Manes. Ths is an extremely helpful plugin that will check all of the dependencies in the project, and see if they have any new versions. Currently, it is configured to only check for stable versions, but you can customize that inside [this Gradle file](/buildscripts/versionsplugin.gradle). 

To run this check, use the following Gradle command:

```
./gradlew dependencyUpdates
```

This will print the updates to the console, as well as a text file you can read from if necessary. 