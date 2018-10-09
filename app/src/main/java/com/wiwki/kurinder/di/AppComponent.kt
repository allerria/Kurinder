package com.wiwki.kurinder.di

import android.app.Application
import com.wiwki.kurinder.app.KurinderApplication
import com.wiwki.kurinder.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ActivityBuilder::class, AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class])
interface AppComponent : AndroidInjector<KurinderApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }

}