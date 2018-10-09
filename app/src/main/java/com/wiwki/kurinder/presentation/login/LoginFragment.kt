package com.wiwki.kurinder.presentation.login

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.wiwki.kurinder.R
import com.wiwki.kurinder.presentation.Screens
import com.wiwki.kurinder.presentation.common.BaseActivity
import com.wiwki.kurinder.presentation.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : BaseFragment(), LoginView {

    override val TAG: String = Screens.LOGIN_SCREEN
    override val layoutRes: Int = R.layout.fragment_login

    @Inject
    @InjectPresenter
    lateinit var presenter: LoginPresenter

    @ProvidePresenter
    fun providePresenter(): LoginPresenter = presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        tutorial_vp.adapter = TutorialPagerAdapter(context)
        tutorial_tl.setupWithViewPager(tutorial_vp)
        login_phone_tv.setOnClickListener {
            presenter.loginViaPhone()
        }
    }
}