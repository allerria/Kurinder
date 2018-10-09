package com.wiwki.kurinder.presentation.profile_create

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.wiwki.kurinder.R
import com.wiwki.kurinder.presentation.Screens
import com.wiwki.kurinder.presentation.common.BaseFragment
import javax.inject.Inject

class ProfileCreateFragment : BaseFragment(), ProfileCreateView {

    override val layoutRes: Int = R.layout.fragment_create_profile
    override val TAG: String = Screens.PROFILE_CREATE_SCREEN

    @Inject
    @InjectPresenter
    lateinit var presenter: ProfileCreatePresenter

    @ProvidePresenter
    fun providePresenter(): ProfileCreatePresenter = presenter
}