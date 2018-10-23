package com.wiwki.kurinder.presentation.login

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.wiwki.kurinder.presentation.Screens
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class LoginPresenter @Inject constructor(private val router: Router) : MvpPresenter<LoginView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Timber.d("login")
    }

    fun loginViaPhone() {
        router.navigateTo(Screens.PhoneScreen())
    }
}