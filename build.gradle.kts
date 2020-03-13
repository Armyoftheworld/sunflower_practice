// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Vers.Plugins.gradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Vers.Plugins.kotlin}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Vers.Dependences.navigationVersion}")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        
    }
}

task<Delete>("clean") {
    delete = setOf(rootProject.buildDir)
}
