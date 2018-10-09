package com.wiwki.kurinder.presentation.code_check

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CodeCheckView: MvpView {
    fun showSuccessSend()
    fun showErrorSend()
    fun showErrorHint()
    fun closeKeyboard()
}