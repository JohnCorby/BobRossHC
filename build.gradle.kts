import com.github.jengelman.gradle.plugins.shadow.tasks.ConfigureShadowRelocation
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.70"

    id("com.github.johnrengelman.shadow") version "5.2.0"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    compileOnly("com.destroystokyo.paper:paper-api:+")

    compileOnly("me.lucko.luckperms:luckperms-api:+")
}

tasks {
    defaultTasks("shadowJar")

    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    task<ConfigureShadowRelocation>("relocate") {
        target = shadowJar.get()
        prefix = project.group.toString()
    }
    shadowJar {
        dependsOn("relocate")
        minimize()
        archiveFileName.set("${project.name}.jar")
    }
}
