package com.wiwki.kurinder.presentation.feed

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class FeedPresenter @Inject constructor(private val router: Router): MvpPresenter<FeedView>() {

}