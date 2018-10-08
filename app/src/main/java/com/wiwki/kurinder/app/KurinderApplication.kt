package com.wiwki.kurinder.app

import android.app.Activity
import android.app.Application
import com.wiwki.kurinder.BuildConfig
import com.wiwki.kurinder.di.DaggerAppComponent
import com.wiwki.kurinder.di.modules.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class KurinderApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        installTimber()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent
            .builder().application(this).networkModule(NetworkModule("url xd")).build()

    private fun installTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}