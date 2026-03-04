plugins {
    kotlin("jvm") version "2.1.20"
    // ESTA ES LA LÍNEA QUE TE FALTA:
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.20"

    // Mantén esta también para las funcionalidades de escritorio
    id("org.jetbrains.compose") version "1.7.3"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    google() // <--- AÑADE ESTA LÍNEA (Imprescindible para androidx)
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/prefer")
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.material)
    implementation(compose.ui)

    // Cambiamos a la versión 1.4.0 que es más universal, o dejamos la 1.4.4
    // pero ahora con el repositorio google() sí la encontrará.
    implementation("androidx.collection:collection:1.4.4")
}

kotlin {
    jvmToolchain(21)
}