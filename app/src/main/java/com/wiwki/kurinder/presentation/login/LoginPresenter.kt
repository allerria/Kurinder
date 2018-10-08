package com.wiwki.kurinder.presentation.login

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import timber.log.Timber

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Timber.d("login")
    }
}