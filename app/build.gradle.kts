plugins {
    kotlin("android")
    id("com.android.application")
}

android {
    compileSdk = Deps.defaultConfigVersions.compileSdkVersion

    defaultConfig {
        applicationId = "com.example.icerocktestgitapp"
        minSdk = Deps.defaultConfigVersions.minSdkVersion
        targetSdk = Deps.defaultConfigVersions.targetSdkVersion
        versionCode = Deps.defaultConfigVersions.versionCode
        versionName = Deps.defaultConfigVersions.versionName

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
    implementation(Deps.androidxDependecies.androidxLegacy)
    testImplementation(Deps.junit.junit)
    androidTestImplementation(Deps.testImplementations.runner)
    androidTestImplementation(Deps.testImplementations.espresso)
    //constraint_layout
    implementation(Deps.androidxDependecies.constraintLayout)
    //material
    implementation(Deps.materialDependecies.materialDesign)
    //navigation_components
    implementation(Deps.navigationDependencies.navFragment)
    implementation(Deps.navigationDependencies.navUI)
}