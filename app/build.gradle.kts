plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    kotlin("plugin.serialization") version "2.1.10"
}

android {
    namespace = "com.example.kasihreview"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.kasihreview"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    val nav_version = "2.9.1"
    implementation("androidx.navigation:navigation-compose:$nav_version")
    val lifecycle_version = "2.9.2"
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${lifecycle_version}")


    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.3")

    val ktor_version = "3.2.3" // check for latest version
    // Ktor client core
    implementation("io.ktor:ktor-client-core:$ktor_version")

    // Engine — CIO is common for JVM/Android
    implementation("io.ktor:ktor-client-cio:$ktor_version")

    // JSON serialization support
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    // Optional: Logging
    implementation("io.ktor:ktor-client-logging:$ktor_version")

    // Kotlinx Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2") // Core coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2") // Android support
    implementation("io.coil-kt:coil-compose:2.6.0")

    implementation("androidx.compose.material3:material3:1.3.2")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    // Converter untuk JSON (pakai Gson)
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
//    // OKHttp Client (optional tapi disarankan)
//    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
}