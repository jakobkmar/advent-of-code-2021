plugins {
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.ajalt.mordant:mordant:2.0.0-beta3")
}

tasks {
    compileJava {
        options.release.set(17)
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }

    sourceSets {
        main {
            java.srcDirs("src")
            resources.srcDirs("input")
        }
    }

    wrapper {
        gradleVersion = "7.3"
    }
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}
