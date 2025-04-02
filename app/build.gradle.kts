plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.appvideojuegos"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.appvideojuegos"
        minSdk = 24
        targetSdk = 35
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.play.services.games)


    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)




    // Replace Retrofit with Volley
    implementation(libs.volley)
    // Keep Gson for JSON parsing
    implementation(libs.retrofit.gson) // You can keep using Gson from Retrofit




    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


}