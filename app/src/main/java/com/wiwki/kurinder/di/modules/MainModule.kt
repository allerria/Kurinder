package com.wiwki.kurinder.di.modules

import com.wiwki.kurinder.presentation.code_check.CodeCheckFragment
import com.wiwki.kurinder.presentation.login.LoginFragment
import com.wiwki.kurinder.presentation.phone.PhoneFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun bindLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun bindPhoneFragment(): PhoneFragment

    @ContributesAndroidInjector
    abstract fun bindCodeCheckFragment(): CodeCheckFragment
}