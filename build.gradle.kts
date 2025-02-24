plugins {
    kotlin("multiplatform") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"

    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
    id("com.adarshr.test-logger") version "3.2.0"
    id("net.rdrei.android.buildtimetracker") version "0.11.0"

    `maven-publish`
    id("org.jetbrains.dokka") version "1.6.10"
}

version = "6.4.0"

object Publications {
    const val GroupId = "blue.starry"
    const val OSSRHProfileGroupId = "blue.starry.jsonkt"
    const val Description = "Full-featured Twitter API wrapper for Kotlin"
    const val GitHubUsername = "StarryBlueSky"
    const val GitHubRepository = "Penicillin"

    const val LicenseName = "The MIT Licence"
    const val LicenseUrl = "https://opensource.org/licenses/MIT"

    const val DeveloperId = "StarryBlueSky"
    const val DeveloperName = "The Starry Blue Sky"
    const val DeveloperEmail = "letter@starry.blue"
    const val DeveloperOrganization = "The Starry Blue Sky"
    const val DeveloperOrganizationUrl = "https://github.com/StarryBlueSky"

    const val MavenCentralStagingRepositoryUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2"
    const val MavenCentralSnapshotRepositoryUrl = "https://oss.sonatype.org/content/repositories/snapshots"
    const val GitHubPackagesRepositoryUrl = "https://maven.pkg.github.com/$GitHubUsername/$GitHubRepository"
}

/*
 * Dependencies
 */

repositories {
    mavenCentral()
    mavenLocal()
}

kotlin {
    explicitApi()

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
    js {
        nodejs()
//        browser {
//            testTask {
//                useKarma {
//                    useChromeHeadless()
//                }
//            }
//        }
    }

    sourceSets {
        commonMain {
            dependencies {
                api("io.ktor:ktor-client-core:2.0.3")

                api("blue.starry:jsonkt:6.2.1")
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

                api("com.benasher44:uuid:0.4.1")
                api("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                api("io.github.microutils:kotlin-logging:2.1.23")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        named("jvmMain") {
        }
        named("jvmTest") {
            dependencies {
                implementation(kotlin("reflect"))
                implementation(kotlin("test"))

                implementation("io.ktor:ktor-client-apache:2.0.3")
                implementation("io.ktor:ktor-client-cio:2.0.3")
                implementation("io.ktor:ktor-client-java:2.0.3")
                implementation("io.ktor:ktor-client-jetty:2.0.3")
                implementation("io.ktor:ktor-client-okhttp:2.0.3")
                implementation("io.ktor:ktor-client-mock-jvm:2.0.3")

                implementation(kotlin("test-junit5"))
                implementation("org.junit.jupiter:junit-jupiter:5.8.2")

                implementation("com.twitter.twittertext:twitter-text:3.1.0")
                implementation("com.google.guava:guava:31.1-jre")

                implementation("ch.qos.logback:logback-classic:1.2.11")
            }
        }

        named("jsMain") {
            dependencies {
                api("io.ktor:ktor-client-js:2.0.3")

                implementation(npm("crypto-js", "4.1.1"))
            }
        }
        named("jsTest") {
            dependencies {
                implementation(kotlin("test-js"))

                implementation("io.ktor:ktor-client-mock-js:2.0.3")
            }
        }
    }

    targets.all {
        compilations.all {
            kotlinOptions {
                apiVersion = "1.7"
                languageVersion = "1.7"
                verbose = true
            }
        }
    }

    sourceSets.all {
        languageSettings {
            progressiveMode = true

            optIn("kotlin.RequiresOptIn")
            optIn("kotlin.time.ExperimentalTime")
            optIn("io.ktor.util.InternalAPI")
            optIn("kotlinx.coroutines.FlowPreview")
            optIn("blue.starry.penicillin.core.experimental.PenicillinExperimentalApi")
        }
    }
}

/*
 * Tests
 */

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
    ignoreFailures.set(true)
}

buildtimetracker {
    reporters {
        register("summary") {
            options["ordered"] = "true"
            options["barstyle"] = "ascii"
            options["shortenTaskNames"] = "false"
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        showStandardStreams = true
        events("passed", "failed")
    }

    testlogger {
        theme = com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA_PARALLEL
    }
}

/*
 * Publishing
 */

//tasks {
//    register<Jar>("kdocJar") {
//        from(dokkaHtml)
//        dependsOn(dokkaHtml)
//        archiveClassifier.set("javadoc")
//    }
//}
//
publishing {
//    repositories {
//        maven {
//            name = "Sonatype"
//            url = uri(
//                if (Env.Version.valueOrNull.orEmpty().endsWith("-SNAPSHOT")) {
//                    Publications.MavenCentralSnapshotRepositoryUrl
//                } else {
//                    Publications.MavenCentralStagingRepositoryUrl
//                }
//            )
//
//            credentials {
//                username = Env.OSSRHUsername.valueOrNull
//                password = Env.OSSRHPassword.valueOrNull
//            }
//        }
//
//        maven {
//            name = "GitHubPackages"
//            url = uri(Publications.GitHubPackagesRepositoryUrl)
//
//            credentials {
//                username = Env.GitHubUsername.valueOrNull
//                password = Env.GitHubPassword.valueOrNull
//            }
//        }
//    }
//
    publications.withType<MavenPublication> {
        groupId = Publications.GroupId
        artifactId = when (name) {
            "kotlinMultiplatform" -> {
                rootProject.name
            }
            else -> {
                "${rootProject.name}-$name"
            }
        }
        version = project.version.toString()
    }
//
//        pom {
//            name.set(artifactId)
//            description.set(Publications.Description)
//            url.set("https://github.com/${Publications.GitHubUsername}/${Publications.GitHubRepository}")
//
//            licenses {
//                license {
//                    name.set(Publications.LicenseName)
//                    url.set(Publications.LicenseUrl)
//                }
//            }
//
//            developers {
//                developer {
//                    id.set(Publications.DeveloperId)
//                    name.set(Publications.DeveloperName)
//                    email.set(Publications.DeveloperEmail)
//                    organization.set(Publications.DeveloperOrganization)
//                    organizationUrl.set(Publications.DeveloperOrganizationUrl)
//                }
//            }
//
//            scm {
//                url.set("https://github.com/${Publications.GitHubUsername}/${Publications.GitHubRepository}")
//            }
//        }
//
//        artifact(tasks["kdocJar"])
//    }
}
//
//signing {
//    setRequired { gradle.taskGraph.hasTask("publish") }
//    sign(publishing.publications)
//
//    if (Env.SigningKey.isPresent) {
//        @Suppress("UnstableApiUsage")
//        useInMemoryPgpKeys(
//            Env.SigningKeyId.value,
//            Env.SigningKey.value,
//            Env.SigningPassword.value
//        )
//    }
//}
//
//nexusStaging {
//    packageGroup = Publications.OSSRHProfileGroupId
//    stagingProfileId = Env.OSSRHProfileId.valueOrNull
//    username = Env.OSSRHUsername.valueOrNull
//    password = Env.OSSRHPassword.valueOrNull
//}
