package com.wiwki.kurinder.presentation.feed

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.wiwki.kurinder.R
import com.wiwki.kurinder.presentation.Screens
import com.wiwki.kurinder.presentation.common.BaseFragment
import javax.inject.Inject


class FeedFragment: BaseFragment(), FeedView {

    override val TAG: String = Screens.FEED_SCREEN
    override val layoutRes: Int = R.layout.fragment_feed

    @Inject
    @InjectPresenter
    lateinit var presenter: FeedPresenter

    @ProvidePresenter
    fun providePresenter(): FeedPresenter = presenter


}