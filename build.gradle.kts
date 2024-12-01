import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
  kotlin("jvm")
  id("org.jetbrains.compose")
  id("org.jetbrains.kotlin.plugin.compose")
  alias(libs.plugins.spotless)
}

group = "io.github.omydagreat"

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
  implementation(libs.richtext.ui)
  implementation(libs.richtext.ui.material)
  implementation(libs.richtext.commonmark)
  implementation(libs.precompose)
  implementation(libs.malefic.nav)
  implementation(libs.malefic.extensions)
  implementation(libs.malefic.components)
  implementation(libs.malefic.prefs)
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
