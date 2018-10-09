package com.wiwki.kurinder.presentation.code_check

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class CodeCheckPresenter @Inject constructor(private val router: Router): MvpPresenter<CodeCheckView>() {

}