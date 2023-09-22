import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    `kotlin-dsl`
}

group = "template.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly("com.android.tools.build:gradle:8.1.1") //    compileOnly(libs.android.gradlePlugin)
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0") //    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.1")
    compileOnly(libs.ktlint.kotlinter.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly("com.github.ben-manes:gradle-versions-plugin:0.48.0")
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "template.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "template.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "template.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "template.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidHilt") {
            id = "template.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidRoom") {
            id = "template.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("androidKtlint") {
            id = "template.android.ktlint"
            implementationClass = "AndroidKotlinterConventionPlugin"
        }
        register("androidDetekt") {
            id = "template.android.detekt"
            implementationClass = "AndroidDetektConventionPlugin"
        }
        register("androidVersionChecker") {
            id = "template.android.version.checker"
            implementationClass = "AndroidVersionUpdateCheckerConventionPlugin"
        }
    }
}
