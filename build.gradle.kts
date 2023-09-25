val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.10"
    id("io.ktor.plugin") version "2.3.4"
}

group = "hu.bme.kszk.inverseCaptcha"
version = "0.0.1"

application {
    mainClass.set("hu.bme.kszk.inverseCaptcha.ApplicationKt")

    val isDevelopment: Boolean = true // project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-sessions-jvm:$ktor_version\"")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version\"")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version\"")
    implementation("io.ktor:ktor-server-freemarker:$ktor_version\"")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version\"")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

kotlin {
    jvmToolchain(17)
}

