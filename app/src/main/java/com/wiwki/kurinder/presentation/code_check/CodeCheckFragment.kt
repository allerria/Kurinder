package com.wiwki.kurinder.presentation.code_check

import android.content.Context
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.wiwki.kurinder.R
import com.wiwki.kurinder.presentation.Screens
import com.wiwki.kurinder.presentation.common.BaseActivity
import com.wiwki.kurinder.presentation.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_code_check.*
import javax.inject.Inject

class CodeCheckFragment : BaseFragment(), CodeCheckView {

    companion object {
        private val VERIFICATION_ID: String = "verificationID"
        private val PHONE: String = "phone"

        fun newInstance(phone: String, verificationId: String): CodeCheckFragment = CodeCheckFragment().apply {
            arguments = Bundle().apply {
                putString(PHONE, phone)
                putString(VERIFICATION_ID, verificationId)
            }
        }
    }

    override val TAG: String = Screens.CODE_CHECK_SCREEN
    override val layoutRes: Int = R.layout.fragment_code_check

    private var phone: String = ""
    private var verificationId: String = ""

    @Inject
    @InjectPresenter
    lateinit var presenter: CodeCheckPresenter

    @ProvidePresenter
    fun providePresenter(): CodeCheckPresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            phone = it.getString(PHONE)
            verificationId = it.getString(VERIFICATION_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {

        phone_tv.text = phone

        code_et.setTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0.let {
                    val padding = submit_code_tv.paddingTop
                    if (it.toString().length >= 6) {
                        submit_code_tv.background = ContextCompat.getDrawable(context!!, R.drawable.border_radius_accent)
                        submit_code_tv.setTextColor(ContextCompat.getColor(context!!, R.color.colorWhite))
                    } else {
                        submit_code_tv.background = ContextCompat.getDrawable(context!!, R.drawable.border_radius)
                        submit_code_tv.setTextColor(ContextCompat.getColor(context!!, R.color.colorPrimaryDark))
                    }
                    submit_code_tv.setPadding(0, padding, 0, padding)
                }
            }
        })

        submit_code_tv.setOnClickListener {
            if (code_et.text.length == 6) {
                presenter.submitCode(verificationId, code_et.text.toString())
            }
        }

        resend_tv.setOnClickListener {
            presenter.sendPhone(activity!! as BaseActivity, phone)
        }

        showSuccessSend()
    }

    override fun showErrorSend() {
        Toast.makeText(context, R.string.oops, Toast.LENGTH_LONG).show()
    }

    override fun showSuccessSend() {
        Toast.makeText(context, R.string.success_send, Toast.LENGTH_LONG).show()
    }

    override fun showErrorHint() {
        code_et.setHint(getString(R.string.code_error))
    }

    override fun closeKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(code_et.windowToken, 0)
    }
}