buildscript {
    extra.apply {
        set("kotlinVersion", "1.5.20")
        set("retrofitVersion", "2.9.0")
        set("navigationVersion", "2.3.5")
        set("daggerVersion", "2.39.1")
        set("gsonVersion", "2.8.6")
        set("jUnitVersion", "4.13.2")
        set("androidTestExtensionVersion", "1.1.3")
    }

    val kotlinVersion: String by extra
    val navigationVersion: String by extra
    val daggerVersion: String by extra

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$daggerVersion")
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}