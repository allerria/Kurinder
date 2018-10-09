package com.wiwki.kurinder.presentation.phone

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
class PhonePresenter @Inject constructor(
        private val router: Router,
        private val authInteractor: AuthInteractor
) : MvpPresenter<PhoneView>() {

    private var phone: String = ""

    private val authCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks by lazy {
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                viewState.showSuccess()
                viewState.stopLoadAnimation()
                authInteractor.signInWithCredential(credential)
                //router navigate to model fill view
            }

            override fun onVerificationFailed(p0: FirebaseException?) {
                viewState.stopLoadAnimation()
                viewState.showErrorSend()
            }

            override fun onCodeSent(verificationId: String, p1: PhoneAuthProvider.ForceResendingToken?) {
                viewState.showSuccess()
                viewState.stopLoadAnimation()
                router.navigateTo(Screens.CodeCheckScreen(phone, verificationId))
            }

            override fun onCodeAutoRetrievalTimeOut(p0: String?) {
                viewState.stopLoadAnimation()
            }
        }
    }

    fun submitPhone(phone: String, activity: BaseActivity) {
        if (phone.matches(Regex("\\+[0-9]{11}"))) {
            this.phone = phone
            authInteractor.verifyPhone(phone, activity, authCallbacks)
            viewState.closeKeyboard()
            viewState.startLoadAnimation()
        } else {
            viewState.showErrorHint()
        }
    }
}