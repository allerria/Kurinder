package com.wiwki.kurinder.presentation.profile_create

import android.graphics.Bitmap
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.wiwki.kurinder.R
import com.wiwki.kurinder.presentation.Screens
import com.wiwki.kurinder.presentation.common.BaseFragment
import com.wiwki.kurinder.util.ProfileDetailEnum
import kotlinx.android.synthetic.main.fragment_create_profile.*
import java.util.*
import javax.inject.Inject

class ProfileCreateFragment : BaseFragment(), ProfileCreateView {

    override val layoutRes: Int = R.layout.fragment_create_profile
    override val TAG: String = Screens.PROFILE_CREATE_SCREEN

    @Inject
    @InjectPresenter
    lateinit var presenter: ProfileCreatePresenter

    @ProvidePresenter
    fun providePresenter(): ProfileCreatePresenter = presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        profile_vp.adapter = ProfilePagerAdapter(context, this::nextProfilePage)
        profile_vp.adapter?.let {
            profile_vp.offscreenPageLimit = it.count
        }
        profile_tl.setupWithViewPager(profile_vp)
    }

    private fun nextProfilePage(dataKey: ProfileDetailEnum, data: Any) {
//        when (dataKey) {
//            ProfileDetailEnum.NAME -> {
//                presenter.setName(data as String)
//            }
//            ProfileDetailEnum.SEX -> {
//                presenter.setMale(data as Boolean)
//            }
//            ProfileDetailEnum.BIRTHDATE -> {
//                presenter.setBirthDate(data as Date)
//            }
//            ProfileDetailEnum.DESCRIPTION -> {
//                presenter.setDescription(data as String)
//            }
//            ProfileDetailEnum.AVATAR -> {
//                presenter.loadAndSetImageUrl(data as Bitmap)
//            }
//        }
        profile_vp.adapter?.let {
            if (profile_vp.currentItem == it.count - 1) {
                return
            }
            profile_vp.getChildAt(profile_vp.currentItem).visibility = View.GONE
            profile_vp.currentItem++
            profile_vp.getChildAt(profile_vp.currentItem).visibility = View.VISIBLE
        }
    }

    fun onBackPressed() {
        if (profile_vp.currentItem == 0) {
            presenter.navigateToPrevScreen()
        } else {
            profile_vp.getChildAt(profile_vp.currentItem).visibility = View.GONE
            profile_vp.currentItem--
            profile_vp.getChildAt(profile_vp.currentItem).visibility = View.VISIBLE
        }
    }
}
