plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
}

android {
    namespace = "ru.ermakov.myanime"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.ermakov.myanime"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Compose Navigation.
    implementation(libs.androidx.navigation.compose)

    // Coroutines.
    implementation(libs.kotlinx.coroutines.android)

    // Koin.
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)

    // Retrofit.
    implementation(libs.retrofit2.retrofit)
    implementation(libs.retrofit2.converter.gson)

    // Room.
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    implementation(project(":core"))
    implementation(project(":database"))
    implementation(project(":feature-anime-api"))
    implementation(project(":feature-anime-impl"))
}