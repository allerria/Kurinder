package com.wiwki.kurinder.di

import com.wiwki.kurinder.di.modules.MainModule
import com.wiwki.kurinder.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainModule::class])
    fun provideMainActivity(): MainActivity
}