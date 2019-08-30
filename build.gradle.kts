import com.github.jengelman.gradle.plugins.shadow.tasks.ConfigureShadowRelocation
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jarName = "${project.name}.jar"
val jarDir = "/home/johncorby/Desktop/Test Server/plugins"

group = "com.johncorby.bobrosshc"

plugins {
    kotlin("jvm") version "1.3.50"

    id("com.github.johnrengelman.shadow") version "5.1.0"
}

repositories {
    maven("https://papermc.io/repo/repository/maven-public")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    compileOnly("com.destroystokyo.paper:paper-api:+")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    shadowJar {
        minimize()

        archiveFileName.set(jarName)
        destinationDirectory.set(File(jarDir))

        dependsOn("relocateShadowJar")
    }

    task<ConfigureShadowRelocation>("relocateShadowJar") {
        target = shadowJar.get()
        prefix = project.group as String
    }
}
