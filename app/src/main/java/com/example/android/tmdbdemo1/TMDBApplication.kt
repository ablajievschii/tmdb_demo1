package com.example.android.tmdbdemo1

import android.app.Application
import timber.log.Timber

class TMDBApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}