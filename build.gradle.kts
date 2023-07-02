import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    application
    id("io.papermc.paperweight.userdev") version "1.5.5"
    id("xyz.jpenilla.run-paper") version "2.1.0"
}

group = "me.glxphs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

val embed: Configuration by configurations.creating
configurations.implementation.get().extendsFrom(embed)

dependencies {
    paperweight.paperDevBundle("1.20.1-R0.1-SNAPSHOT")

//    compileOnly("io.papermc.paper:paper-api:1.20-R0.1-SNAPSHOT")
//    compileOnly("io.papermc.paper:paper:1.20-R0.1-SNAPSHOT")
    embed("org.jetbrains.kotlin:kotlin-stdlib")
    embed("com.google.guava:guava:30.1.1-jre")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks.jar {
    from(embed.files.map { zipTree(it) })

//    archiveFileName.set("glxphsPlugin.jar")
//    destinationDirectory.set(file("./server/plugins"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("MainKt")
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name()
//        val props = mapOf(
//            "name" to project.name,
//            "version" to project.version,
//            "description" to project.description,
//            "apiVersion" to "1.20"
//        )
//        inputs.properties(props)
//        filesMatching("plugin.yml") {
//            expand(props)
//        }
    }
    reobfJar {
        // This is an example of how you might change the output location for reobfJar. It's recommended not to do this
        // for a variety of reasons, however it's asked frequently enough that an example of how to do it is included here.
        outputJar.set(layout.buildDirectory.file("../server/plugins/glxphsPlugin.jar"))
    }
}