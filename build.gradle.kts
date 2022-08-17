/*
 * Copyright 2022 Mikhail Titov and other contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java-library")
    id("maven-publish")
    kotlin("jvm") version "1.7.10"
}

group = "dev.d1s"
version = "1.0.0"

java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    val ktorVersion: String by project
    val liquibaseVersion: String by project
    val kmlogVersion: String by project

    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    api("org.liquibase:liquibase-core:$liquibaseVersion")
    implementation("org.lighthousegames:logging-jvm:$kmlogVersion")
}

publishing {
    publications {
        create<MavenPublication>("ktor-server-liquibase") {
            from(components["java"])
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

kotlin {
    explicitApi()
}