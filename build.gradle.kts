import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.compose.kotlin)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.compose)
}

group = "xyz.malefic"

version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.foundation)
    implementation(compose.animation)
    implementation(libs.coroutines.core)
    implementation(libs.filekit.core)
    implementation(libs.kermit)
    implementation(libs.precompose)
    implementation(libs.bundles.richtext)
    implementation(libs.bundles.malefic.compose)
    implementation(libs.bundles.malefic.ext)
}

compose.desktop {
    application {
        mainClass = "xyz.malefic.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "baka"
            packageVersion = "1.0.0"
            linux { modules("jdk.security.auth") }
        }
    }
}
