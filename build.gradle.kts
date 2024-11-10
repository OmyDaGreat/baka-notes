import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
  kotlin("jvm")
  id("org.jetbrains.compose")
  id("org.jetbrains.kotlin.plugin.compose")
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

dependencies {
  // Note, if you develop a library, you should use compose.desktop.common.
  // compose.desktop.currentOs should be used in launcher-sourceSet
  // (in a separate module for demo project and in testMain).
  // With compose.desktop.common you will also lose @Preview functionality
  implementation(compose.desktop.currentOs)
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
  implementation("io.github.vinceglb:filekit-core:$filekitVersion")
  implementation("co.touchlab:kermit:$kermitVersion")
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
