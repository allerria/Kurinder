package com.wiwki.kurinder.presentation.login

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.wiwki.kurinder.R
import com.wiwki.kurinder.presentation.common.BaseFragment

class LoginFragment : BaseFragment(), LoginView {
    override val TAG: String = "LoginFragment"
    override val layoutRes: Int = R.layout.fragment_login

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    @ProvidePresenter
    fun providePresenter(): LoginPresenter = LoginPresenter()
}