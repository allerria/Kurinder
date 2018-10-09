package com.wiwki.kurinder.presentation.phone

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface PhoneView: MvpView {
    fun showErrorHint()
    fun showErrorSend()
    fun startLoadAnimation()
    fun stopLoadAnimation()
    fun showSuccess()
    fun closeKeyboard()
}