import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "com.shinshila"
version = "v4"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.alibaba:fastjson:2.0.7")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.0")
    implementation("org.sejda.imageio:webp-imageio:0.1.6")
}
tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}