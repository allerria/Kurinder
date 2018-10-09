package com.wiwki.kurinder.di.modules

import dagger.Module

@Module(includes = [NetworkModule::class, NavigationModule::class])
class AppModule {

}