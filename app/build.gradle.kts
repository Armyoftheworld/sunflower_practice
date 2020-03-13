plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Vers.Android.compileSdkVersion)
    dataBinding {
        isEnabled = true
    }
    defaultConfig {
        applicationId = "com.daijun.sunflower.practice"
        minSdkVersion(Vers.Android.minSdkVersion)
        targetSdkVersion(Vers.Android.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
//    // work-runtime-ktx 2.1.0 and above now requires Java 8
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
    resolutionStrategy.cacheDynamicVersionsFor(0, "seconds")
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Vers.Plugins.kotlin}")
    implementation("androidx.appcompat:appcompat:${Vers.Dependences.appCompatVersion}")
    implementation("androidx.core:core-ktx:${Vers.Dependences.ktxVersion}")
    implementation("androidx.constraintlayout:constraintlayout:${Vers.Dependences.constraintLayoutVersion}")
    implementation("androidx.fragment:fragment-ktx:${Vers.Dependences.fragmentVersion}")
    implementation("androidx.lifecycle:lifecycle-extensions:${Vers.Dependences.lifecycleVersion}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Vers.Dependences.lifecycleVersion}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Vers.Dependences.lifecycleVersion}")
    implementation("androidx.navigation:navigation-fragment-ktx:${Vers.Dependences.navigationVersion}")
    implementation("androidx.navigation:navigation-ui-ktx:${Vers.Dependences.navigationVersion}")
    implementation("androidx.recyclerview:recyclerview:${Vers.Dependences.recyclerViewVersion}")
    implementation("androidx.room:room-runtime:${Vers.Dependences.roomVersion}")
    implementation("androidx.room:room-ktx:${Vers.Dependences.roomVersion}")
    implementation("androidx.viewpager2:viewpager2:${Vers.Dependences.viewPager2Version}")
    implementation("androidx.work:work-runtime-ktx:${Vers.Dependences.workVersion}")
    implementation("com.github.bumptech.glide:glide:${Vers.Dependences.glideVersion}")
    implementation("com.google.android.material:material:${Vers.Dependences.materialVersion}")
    implementation("com.google.code.gson:gson:${Vers.Dependences.gsonVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Vers.Dependences.coroutinesVersion}")
    kapt("androidx.room:room-compiler:${Vers.Dependences.roomVersion}")
    kapt("com.github.bumptech.glide:compiler:${Vers.Dependences.glideVersion}")
}

afterEvaluate {
    println("extensions = ${extensions.getByName("android")}")
}
