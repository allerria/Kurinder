package com.wiwki.kurinder.presentation.phone

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.wiwki.kurinder.presentation.Screens
import com.wiwki.kurinder.presentation.common.BaseActivity
import ru.terrakok.cicerone.Router
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class PhonePresenter @Inject constructor(private val router: Router) : MvpPresenter<PhoneView>() {

    private val authCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks by lazy {
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential?) {
                viewState.showSuccess()
                //router navigate to model fill view
            }

            override fun onVerificationFailed(p0: FirebaseException?) {
                viewState.stopLoadAnimation()
                viewState.showErrorSend()
            }

            override fun onCodeSent(p0: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
                viewState.showSuccess()
                router.navigateTo(Screens.CodeCheckScreen())
            }

            override fun onCodeAutoRetrievalTimeOut(p0: String?) {
                viewState.stopLoadAnimation()
                viewState.showErrorSend()
            }
        }
    }

    private val phoneAuthProvider: PhoneAuthProvider by lazy { PhoneAuthProvider.getInstance() }

    fun submitPhone(phone: String, activity: BaseActivity) {
        if (phone.matches(Regex("\\+[0-9]{11}"))) {
            phoneAuthProvider.verifyPhoneNumber(phone, 60, TimeUnit.SECONDS, activity, authCallbacks)
            viewState.startLoadAnimation()
        } else {
            viewState.showErrorHint()
        }
    }
}