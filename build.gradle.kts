import com.github.jengelman.gradle.plugins.shadow.tasks.ConfigureShadowRelocation
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

    task<ConfigureShadowRelocation>("relocateShadowJar") {
        target = shadowJar.get()
        prefix = project.group.toString()
    }

    shadowJar {
        dependsOn("relocateShadowJar")

        minimize()

        archiveFileName.set("${project.name}.jar")
        destinationDirectory.set(File("/home/johncorby/Desktop/Test Server/plugins"))
    }
}
