package com.wiwki.kurinder.domain

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.wiwki.kurinder.presentation.common.BaseActivity
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthInteractor @Inject constructor() {

    private val TIMEOUT_IN_SECONDS: Long = 60L

    private val phoneAuthProvider: PhoneAuthProvider by lazy { PhoneAuthProvider.getInstance() }

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    fun verifyCodeAndSignIn(verificationId: String, code: String): Task<AuthResult> {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        return auth.signInWithCredential(credential)
    }

    fun verifyPhone(phone: String, activity: BaseActivity, authCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks) {
        phoneAuthProvider.verifyPhoneNumber(phone, TIMEOUT_IN_SECONDS, TimeUnit.SECONDS, activity, authCallbacks)
    }

    fun signInWithCredential(credential: AuthCredential) {
        auth.signInWithCredential(credential)
    }

    fun isLogin(): Boolean = auth.currentUser != null
}