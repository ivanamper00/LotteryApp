plugins {
    id ("com.android.application")
    kotlin ("android")
    kotlin ("kapt")
    id ("dagger.hilt.android.plugin")
    id ("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = BuildConfig.COMPILED_SDK

    defaultConfig {
        applicationId = BuildConfig.APPLICATION_ID
        minSdk = BuildConfig.MIN_SDK
        targetSdk = BuildConfig.TARGET_SDK
        versionCode = BuildConfig.VERSION_CODE
        versionName = BuildConfig.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        android.buildFeatures.viewBinding = true
        buildFeatures.dataBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation (AndroidX.MATERIAL)
    implementation (AndroidX.CORE_KTX)
    implementation (AndroidX.APP_COMPAT)
    implementation (AndroidX.CONSTRAINT_LAYOUT)
    implementation (AndroidX.LEGACY_SUPPORT)
    testImplementation (Test.JUNIT)
    androidTestImplementation (Test.JUNIT_EXT)
    androidTestImplementation (Test.ESPRESSO)


    //Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:2.40.4")
    kapt ("com.google.dagger:hilt-android-compiler:2.40.4")
    implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt ("androidx.hilt:hilt-compiler:1.0.0")

    //RxJava
    implementation ("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation ("io.reactivex.rxjava2:rxkotlin:2.3.0")
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")

    //Epoxy
    implementation ("com.airbnb.android:epoxy:4.6.3")
    kapt ("com.airbnb.android:epoxy-processor:4.6.3")

    //navigation component
    implementation ("androidx.navigation:navigation-fragment-ktx:2.4.2")
    implementation ("androidx.navigation:navigation-ui-ktx:2.4.2")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:converter-scalars:2.9.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    implementation ("com.squareup.retrofit2:converter-simplexml:2.9.0")

    //viewmodel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0-rc01")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.0-rc01")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.0-rc01")

    //Interceptor
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")

    //Room Database
    implementation ("androidx.room:room-runtime:2.4.2")
    implementation ("androidx.room:room-rxjava2:2.4.2")
    kapt ("androidx.room:room-compiler:2.4.2")
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")

    //JumpCode
    implementation ("com.github.ivanamper00:TalonNiDaku:3.1.5")

}