package com.wiwki.kurinder.presentation.phone

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat.getSystemService
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.wiwki.kurinder.R
import com.wiwki.kurinder.presentation.Screens
import com.wiwki.kurinder.presentation.common.BaseActivity
import com.wiwki.kurinder.presentation.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_phone.*
import javax.inject.Inject

class PhoneFragment : BaseFragment(), PhoneView {

    override val TAG: String = Screens.PHONE_SCREEN
    override val layoutRes: Int = R.layout.fragment_phone

    @Inject
    @InjectPresenter
    lateinit var presenter: PhonePresenter

    @ProvidePresenter
    fun providePresenter(): PhonePresenter = presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        focusPhoneEditText()
        submit_phone_tv.setOnClickListener {
            presenter.submitPhone(phone_et.text.toString(), activity!! as BaseActivity)
        }
    }

    override fun showErrorHint() {
        phone_et.error = getString(R.string.phone_error)
    }

    override fun showErrorSend() {
        Toast.makeText(context, R.string.oops, Toast.LENGTH_LONG).show()
    }

    override fun startLoadAnimation() {
        phone_cl.visibility = View.GONE
        spin_kit.visibility = View.VISIBLE
    }

    override fun stopLoadAnimation() {
        phone_cl.visibility = View.VISIBLE
        spin_kit.visibility = View.GONE
    }

    override fun closeKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(phone_et.windowToken, 0)
    }

    private fun focusPhoneEditText() {
        phone_et.requestFocus()
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(phone_et, InputMethodManager.SHOW_IMPLICIT)
    }
}