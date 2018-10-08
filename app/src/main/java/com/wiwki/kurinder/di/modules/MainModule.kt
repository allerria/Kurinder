package com.wiwki.kurinder.di.modules

import com.wiwki.kurinder.presentation.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {
    @ContributesAndroidInjector
    abstract fun bindLoginFragment(): LoginFragment
}