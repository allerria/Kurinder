package com.wiwki.kurinder.presentation.profile_create

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProfileCreateView: MvpView {
    fun showError(e: Exception)
    fun stopLoadAnimation()
    fun showLoadAnimation()
}