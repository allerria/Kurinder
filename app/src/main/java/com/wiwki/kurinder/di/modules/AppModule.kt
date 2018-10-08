package com.wiwki.kurinder.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, NavigationModule::class])
class AppModule {

}