import com.sun.org.apache.bcel.internal.Repository
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.30"
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
    compile("io.github.microutils:kotlin-logging:1.12.5")
    implementation("com.github.ajalt:clikt:1.7.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}