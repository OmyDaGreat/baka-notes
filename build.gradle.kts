import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
  kotlin("jvm")
  id("org.jetbrains.compose")
  id("org.jetbrains.kotlin.plugin.compose")
  id("com.diffplug.spotless") version "7.0.0.BETA4"
}

group = "io.github.omydagreat"

version = "1.0.0"

repositories {
  mavenCentral()
  maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  google()
}

val coroutinesVersion: String by project
val filekitVersion: String by project
val kermitVersion: String by project
val richTextVersion: String by project
val precomposeVersion: String by project

dependencies {
  implementation(compose.desktop.currentOs)
  implementation(compose.foundation)
  implementation(compose.animation)
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
  implementation("io.github.vinceglb:filekit-core:$filekitVersion")
  implementation("co.touchlab:kermit:$kermitVersion")
  implementation("com.halilibo.compose-richtext:richtext-ui:$richTextVersion")
  implementation("com.halilibo.compose-richtext:richtext-commonmark:$richTextVersion")
  implementation("moe.tlaster:precompose:$precomposeVersion")
}

compose.desktop {
  application {
    mainClass = "io.github.omydagreat.MainKt"

    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = "baka"
      packageVersion = "1.0.0"
      linux { modules("jdk.security.auth") }
    }
  }
}

spotless {
  kotlin {
    ktfmt().googleStyle()
  }
}
