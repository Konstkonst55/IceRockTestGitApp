class Deps {
    object Versions{
        const val nav_version = "2.4.2"
        const val legacy_version = "1.0.0"
        const val junit_version = "4.13.2"
        const val runner_version = "1.0.2"
        const val espresso_version = "3.0.2"
        const val constraint_version = "2.1.3"
        const val material_version = "1.6.0"
    }

    object defaultConfigVersions{
        const val compileSdkVersion = 32
        const val minSdkVersion = 21
        const val targetSdkVersion = 32
        const val versionCode = 1
        const val versionName = "1.0"
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

    object hiltDependecies{
        //todo
    }

    object kotlinDependencies{
        //todo
    }

    object retrofitDependencies{
        //todo
    }
}