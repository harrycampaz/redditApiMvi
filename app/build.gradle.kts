
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.harrycampaz.redditapi"
        minSdk = 21
        targetSdk = 31
        versionCode  =1
        versionName ="1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro" )
        }
    }
    compileOptions {
        sourceCompatibility =  JavaVersion.VERSION_1_8
        targetCompatibility =  JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {


    implementation(fileTree("dir" to "libs", "include" to  listOf("*.jar")))

    // Kotlin
    implementation (KotlinLibraries.kotlinStdLibrary)

    // Coroutines
    implementation (KotlinLibraries.coroutinesCore)
    implementation(KotlinLibraries.coroutinesAndroid)

    // Android
    implementation(AndroidLibraries.playCoreKtx)
    implementation(AndroidLibraries.core)
    implementation(AndroidLibraries.appCompat)
    implementation(AndroidLibraries.constraintLayout)

    // Material Design
    implementation(AndroidLibraries.material)

    // Navigation Components
    implementation(AndroidLibraries.navigationFragment)
    implementation(AndroidLibraries.navigationUi)

    // Room and Architectural Components
    implementation (PersistenceLibraries.roomRuntime)
    implementation (PersistenceLibraries.roomKtx)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    kapt(PersistenceLibraries.xerialSqite)
    kapt (PersistenceLibraries.roomCompiler)

    // TEST

    testImplementation (TestLibraries.truth)
    testImplementation (TestLibraries.okhttp3Mockwebserver)
    testImplementation (TestLibraries.mockk)
    implementation (LogTools.timber)
    testImplementation( TestLibraries.coroutines)
    testImplementation (TestLibraries.jUnit)
    androidTestImplementation (TestLibraries.androidJUnit)
}