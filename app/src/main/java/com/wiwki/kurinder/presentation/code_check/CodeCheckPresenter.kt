package com.wiwki.kurinder.presentation.code_check

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.wiwki.kurinder.domain.AuthInteractor
import com.wiwki.kurinder.presentation.Screens
import com.wiwki.kurinder.presentation.common.BaseActivity
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class CodeCheckPresenter @Inject constructor(
        private val router: Router,
        private val authInteractor: AuthInteractor
) : MvpPresenter<CodeCheckView>() {

    private val authCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks by lazy {
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential?) {}

            override fun onVerificationFailed(p0: FirebaseException?) {
                viewState.showErrorSend()
            }

            override fun onCodeSent(p0: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
                viewState.showSuccessSend()
            }

            override fun onCodeAutoRetrievalTimeOut(p0: String?) {}
        }
    }

    fun submitCode(verificationId: String, code: String) {
        authInteractor.verifyCodeAndSignIn(verificationId, code).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                viewState.closeKeyboard()
                router.navigateTo(Screens.ProfileCreateScreen())
            } else {
                viewState.showErrorHint()
            }
        }
    }

    fun sendPhone(activity: BaseActivity, phone: String) {
        authInteractor.verifyPhone(phone, activity, authCallbacks)
    }
}