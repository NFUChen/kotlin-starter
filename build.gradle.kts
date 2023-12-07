plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "com.kotlin.starer"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}

val mainClass = "MainKt"

tasks.jar {
    manifest.attributes["Main-Class"] = mainClass
    val dependencies = configurations.runtimeClasspath.get().map {
        zipTree(it)
    }
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

}