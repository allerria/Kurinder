package com.wiwki.kurinder.presentation.code_check

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.wiwki.kurinder.R
import com.wiwki.kurinder.presentation.common.BaseActivity
import com.wiwki.kurinder.presentation.common.BaseFragment
import javax.inject.Inject

class CodeCheckFragment : BaseFragment(), CodeCheckView {

    override val TAG: String = "CodeCheckFragment"
    override val layoutRes: Int = R.layout.fragment_code_check

    @Inject
    @InjectPresenter
    lateinit var presenter: CodeCheckPresenter

    @ProvidePresenter
    fun providePresenter(): CodeCheckPresenter = presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    override fun onResume() {
        super.onResume()
        (activity as BaseActivity?)?.supportActionBar?.hide()
    }

    override fun onPause() {
        (activity as BaseActivity?)?.supportActionBar?.show()
        super.onPause()
    }

    private fun initUI() {

    }
}