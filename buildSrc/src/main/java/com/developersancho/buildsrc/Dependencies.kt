package com.developersancho.buildsrc

object Versions {
    const val appCompat = "1.1.0"
    const val coreKtx = "1.3.0"
    const val constraintLayout = "1.1.3"
    const val material = "1.1.0"
    const val swipeRefreshLayout = "1.0.0"
    const val lifecycle = "2.2.0"
    const val coroutine = "1.3.7"
    const val coil = "0.11.0"
    const val wada811 = "4.0.0"
    const val koin = "2.1.5"
    const val debugDb = "1.0.6"
    const val gson = "2.8.6"
    const val retrofit = "2.9.0"
    const val okhttp = "4.7.2"
    const val conscrypt = "2.4.0"
    const val room = "2.2.5"

    const val junit = "4.13"
    const val junitExt = "1.1.1"
    const val espresso = "3.2.0"
    const val roboelectric = "4.3.1"
    const val mockwebserver = "4.6.0"
}

object Libs {

    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val material = "com.google.android.material:material:${Versions.material}"
    val swipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"

    val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"

    val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"

    val coil = "io.coil-kt:coil:${Versions.coil}"
    val wada811 = "com.github.wada811:DataBinding-ktx:${Versions.wada811}"

    val koinCore = "org.koin:koin-core:${Versions.koin}"
    val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    val debugDb = "com.amitshekhar.android:debug-db:${Versions.debugDb}"

    val gson = "com.google.code.gson:gson:${Versions.gson}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    val conscrypt = "org.conscrypt:conscrypt-android:${Versions.conscrypt}"

    val room = "androidx.room:room-ktx:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"


    val junit = "junit:junit:${Versions.junit}"
    val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val koinTest = "org.koin:koin-test:${Versions.koin}"
    val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine}"
    val roboelectric = "org.robolectric:robolectric:${Versions.roboelectric}"
    val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.mockwebserver}"
}