class Deps {
    object Versions{
        const val nav_version = "2.4.2"
        const val legacy_version = "1.0.0"
        const val junit_version = "4.13.2"
        const val runner_version = "1.0.2"
        const val espresso_version = "3.0.2"
        const val constraint_version = "2.1.3"
        const val material_version = "1.6.0"
        const val hilt_version = "2.42"
        const val retrofit_version = "2.9.0"
        const val retrofit_converter = "0.8.0"
        const val lifecycle_version = "2.4.1"

        const val kotlin_version = "1.6.21"
        const val kotlin_serialization_version = "1.3.3"
    }

    object defaultConfigVersions{
        const val compileSdkVersion = 32
        const val minSdkVersion = 21
        const val targetSdkVersion = 32
        const val versionCode = 1
        const val versionName = "1.0"
    }

    object kotlinDependecies{
        const val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlin_serialization_version}"
    }

    object hiltDependencies{
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt_version}"
        const val hilt_kapt = "com.google.dagger:hilt-compiler:${Versions.hilt_version}"
    }

    object androidxDependecies{
        const val androidxLegacy = "androidx.legacy:legacy-support-v4:${Versions.legacy_version}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_version}"
    }

    object junit{
        const val junit = "junit:junit:${Versions.junit_version}"
    }

    object navigationDependencies{
        const val navUI = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
        const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    }

    object materialDependecies{
        const val materialDesign = "com.google.android.material:material:${Versions.material_version}"
    }

    object testImplementations{
        const val runner = "com.android.support.test:runner:${Versions.runner_version}"
        const val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espresso_version}"
    }

    object retrofitDependencies{
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
        const val retrofitConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofit_converter}"
    }

    object lifecycleDependecies{
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}"
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    }
}