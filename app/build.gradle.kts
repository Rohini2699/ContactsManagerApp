plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //id("com.google.devtools.ksp")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.contactsmanagerapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.contactsmanagerapp"
        minSdk = 34
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        dataBinding=true
    }


}

dependencies {

   //Room Database
    implementation ("androidx.room:room-runtime:2.6.1")
    implementation("androidx.core:core-ktx:1.13.1")
    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    // Room With coroutines
    implementation("androidx.room:room-ktx:2.6.1")
    // kotlin sybmol processing (KSP)
    //here we have to add the plugin for the ksp annotation processor
    // view model dependency
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0")
    // Live data dependency
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.0")
    kapt ("androidx.room:room-compiler:2.6.1")

   // ksp ("androidx.room:room-compiler:2.6.1")
    // Here why we are not using ksp because databinding is not supported using ksp it is limited because of that we are using the kapt.
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}