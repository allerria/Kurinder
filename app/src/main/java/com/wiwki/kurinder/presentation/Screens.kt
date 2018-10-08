package com.wiwki.kurinder.presentation

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.wiwki.kurinder.presentation.login.LoginFragment
import com.wiwki.kurinder.presentation.main.MainActivity
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    companion object {
        class MainScreen : SupportAppScreen() {
            override fun getActivityIntent(context: Context?): Intent = Intent(context, MainActivity::class.java)
        }

        class LoginScreen: SupportAppScreen() {
            override fun getFragment(): Fragment = LoginFragment()
        }
    }
}