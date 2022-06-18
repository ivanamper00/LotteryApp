object Versions {
    const val DAGGER = "2.40.4"
    const val NAV_COMPONENT = "2.4.2"
    const val MATERIAL = "1.6.1"
    const val KOTLIN = "1.8.0"
    const val APP_COMPAT = "1.4.2"
    const val CONSTRAINT_LAYOUT = "2.1.4"
    const val LEGACY_SUPPORT = "1.0.0"
    const val JUNIT = "4.13.2"
    const val JUNIT_EXT = "1.1.3"
    const val ESPRESSO = "3.4.0"
}

object DaggerHilt {
    const val PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:${Versions.DAGGER}"
}

object NavigationComponent {
    const val PLUGIN = "androidx.navigation.safeargs:androidx.navigation.safeargs.gradle.plugin:${Versions.NAV_COMPONENT}"
}


object AndroidX {
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.KOTLIN}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val LEGACY_SUPPORT = "androidx.legacy:legacy-support-v4:${Versions.LEGACY_SUPPORT}"
}

object Test {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val JUNIT_EXT = "androidx.test.ext:junit:${Versions.JUNIT_EXT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
}