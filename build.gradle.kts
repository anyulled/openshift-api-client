import com.sun.org.apache.bcel.internal.Repository
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.20"
    application
}

group = "com.db.dis"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

application {
    mainClassName = "Main"
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("io.github.microutils:kotlin-logging:3.0.5")
    implementation("com.github.ajalt:clikt:2.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}