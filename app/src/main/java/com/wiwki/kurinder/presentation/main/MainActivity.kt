package com.wiwki.kurinder.presentation.main

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.wiwki.kurinder.R
import com.wiwki.kurinder.presentation.common.BaseActivity
import com.wiwki.kurinder.presentation.profile_create.ProfileCreateFragment
import com.wiwki.kurinder.util.SupportAppNavigatorX
import ru.terrakok.cicerone.Navigator
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    override val layoutRes: Int = R.layout.activity_main

    override val navigator: Navigator = object : SupportAppNavigatorX(this, R.id.main_container) {}

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        return presenter
    }

    override fun onBackPressed() {
        val firstFragment = supportFragmentManager.fragments.firstOrNull()
        if (firstFragment != null && firstFragment is ProfileCreateFragment) {
            firstFragment.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }
}
