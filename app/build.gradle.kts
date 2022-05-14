
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

object LibraryVersions{
    const val nav_version = "2.4.2"
    const val legacy_version = "1.0.0"
    const val junit_version = "4.13.2"
    const val runner_version = "1.0.2"
    const val espresso_version = "3.0.2"
    const val constraint_version = "2.0.4"
    const val material_version = "1.6.0"
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.icerocktestgitapp"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {


    implementation("androidx.legacy:legacy-support-v4:${LibraryVersions.legacy_version}")
    testImplementation("junit:junit:${LibraryVersions.junit_version}")
    androidTestImplementation("com.android.support.test:runner:${LibraryVersions.runner_version}")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:${LibraryVersions.espresso_version}")
    //constraint_layout
    implementation("com.android.support.constraint:constraint-layout:${LibraryVersions.constraint_version}")
    //material
    implementation("com.google.android.material:material:${LibraryVersions.material_version}")
    //navigation_components
    implementation("androidx.navigation:navigation-fragment-ktx:${LibraryVersions.nav_version}")
    implementation("androidx.navigation:navigation-ui-ktx:${LibraryVersions.nav_version}")
}