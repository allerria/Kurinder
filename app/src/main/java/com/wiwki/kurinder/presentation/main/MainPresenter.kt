package com.wiwki.kurinder.presentation.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.wiwki.kurinder.domain.AuthInteractor
import com.wiwki.kurinder.presentation.Screens
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
        private val router: Router,
        private val authInteractor: AuthInteractor
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Timber.d("perviy privew")
        if (authInteractor.isLogin()) {
            router.newRootScreen(Screens.ProfileCreateScreen())//пойти на норм экран
        } else {
            router.newRootScreen(Screens.LoginScreen())
        }
    }
}