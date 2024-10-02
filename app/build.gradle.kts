plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
//    id ("kotlin-kapt")
}

android {
    namespace = "com.example.tvshows"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tvshows"
        minSdk = 24
        targetSdk = 34
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
    buildFeatures {
        viewBinding = true
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //Material Design
    implementation (libs.material.v190)
    //Retrofit
    implementation (libs.retrofit)
    //Gson
    implementation (libs.converter.gson)
    //Picasso
    implementation (libs.picasso)
    //Lifecycle Extensions
    implementation (libs.androidx.lifecycle.extensions)
    //Rounded Image
    implementation (libs.roundedimageview)
    //DataBinding
//    kapt(libs.androidx.databinding.compiler)


}