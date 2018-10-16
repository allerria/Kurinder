package com.wiwki.kurinder.presentation

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.wiwki.kurinder.presentation.code_check.CodeCheckFragment
import com.wiwki.kurinder.presentation.login.LoginFragment
import com.wiwki.kurinder.presentation.main.MainActivity
import com.wiwki.kurinder.presentation.phone.PhoneFragment
import com.wiwki.kurinder.presentation.profile_create.ProfileCreateFragment
import com.wiwki.kurinder.util.SupportAppScreenX

object Screens {

    const val CODE_CHECK_SCREEN = "CodeCheckFragment"
    const val LOGIN_SCREEN = "loginFragment"
    const val PHONE_SCREEN = "phoneFragment"
    const val PROFILE_CREATE_SCREEN = "profileCreateFragment"

    class MainScreen : SupportAppScreenX() {
        override fun getActivityIntent(context: Context?): Intent = Intent(context, MainActivity::class.java)
    }

    class LoginScreen : SupportAppScreenX() {
        override fun getFragment(): Fragment = LoginFragment()
    }

    class PhoneScreen : SupportAppScreenX() {
        override fun getFragment(): Fragment = PhoneFragment()
    }

    class CodeCheckScreen(private val phone: String, private val verificationId: String) : SupportAppScreenX() {
        override fun getFragment(): Fragment = CodeCheckFragment.newInstance(phone, verificationId)
    }

    class ProfileCreateScreen() : SupportAppScreenX() {
        override fun getFragment(): Fragment = ProfileCreateFragment()
    }
}