buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Deps.Versions.hilt_version}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.6.21")
        classpath("com.android.tools.build:gradle:7.2.0")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.2")
    }
}

plugins{
    id("org.jetbrains.kotlin.android") version "1.6.21" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.21" apply false
    id("com.android.application") version "7.2.0" apply false
    id("com.android.library") version "7.2.0" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}