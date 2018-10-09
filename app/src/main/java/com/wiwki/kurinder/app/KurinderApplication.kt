package com.wiwki.kurinder.app

import com.wiwki.kurinder.BuildConfig
import com.wiwki.kurinder.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class KurinderApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        installTimber()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent
            .builder().application(this).build()

    private fun installTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}