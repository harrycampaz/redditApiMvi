object AndroidLibraries {
    object Versions {
        const val core = "1.7.0"
        const val appCompat = "1.4.0"
        const val constraintLayout = "2.1.2"
        const val material = "1.4.0"
        const val lifecycleViewModel = "2.4.0"
        const val lifecycleLiveData = "2.4.0"
        const val lifecycleExtensions = "2.2.0"
        const val fragmentKtx = "1.4.0"
        const val navigation = "2.3.5"
        const val sharedPreferences = "1.1.1"
        const val playCore = "1.10.2"
        const val playCoreKtx = "1.8.1"
        const val dataStore  = "1.0.0"
        const val paging = "3.1.0"
    }

    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val material = "com.google.android.material:material:${Versions.material}"

    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModel}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleLiveData}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"


    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"


    const val playCore = "com.google.android.play:core:${Versions.playCore}"

    const val playCoreKtx = "com.google.android.play:core-ktx:${Versions.playCoreKtx}"

}

object KotlinLibraries {
    object Versions {
        const val kotlin = "1.5.10"
        const val coroutines = "1.5.2"
    }
    const val kotlinStdLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object PersistenceLibraries{
    object Version {
        const val room ="2.3.0"
        const val xerialSqite = "3.36.0"
    }
    const val roomRuntime = "androidx.room:room-runtime:${Version.room}"
    const val roomKtx = "androidx.room:room-ktx:${Version.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.room}"
    const val xerialSqite = "org.xerial:sqlite-jdbc:${Version.xerialSqite}"
}


object TestLibraries {
    object Versions {
        const val jUnit = "4.13.2"
        const val androidJUnit = "1.1.3"
        const val mockk = "1.10.2"
        const val coroutines = "1.5.0"
        const val truth = "1.1.2"

        const val okhttp3Mockwebserver = "4.9.0"
    }

    const val okhttp3Mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp3Mockwebserver}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val androidJUnit = "androidx.test.ext:junit:${Versions.androidJUnit}"

    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}
object DILibraries {
    object Versions {
        const val koin = "3.1.4"
    }

    const val koin = "io.insert-koin:koin-android:${Versions.koin}"
}

object LogTools {
    object Versions {
        const val timber = "4.7.1"
    }
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object ThirdLibraries{
    object Versions {
        const val glide = "4.12.0"
        const val glideKpt = "4.11.0"
    }

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideKpt = "com.github.bumptech.glide:compiler:${Versions.glideKpt}"

}