package com.wiwki.kurinder.presentation.profile_create

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.wiwki.kurinder.R
import com.wiwki.kurinder.presentation.Screens
import com.wiwki.kurinder.presentation.common.BaseFragment
import com.wiwki.kurinder.util.ProfileDetailEnum
import kotlinx.android.synthetic.main.fragment_create_profile.*
import timber.log.Timber
import javax.inject.Inject
import android.content.DialogInterface
import android.support.v7.app.AlertDialog


class ProfileCreateFragment : BaseFragment(), ProfileCreateView {

    override val layoutRes: Int = R.layout.fragment_create_profile
    override val TAG: String = Screens.PROFILE_CREATE_SCREEN

    private val GALLERY = 1
    private val CAMERA = 2

    @Inject
    @InjectPresenter
    lateinit var presenter: ProfileCreatePresenter

    @ProvidePresenter
    fun providePresenter(): ProfileCreatePresenter = presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Timber.d("sada")
        //TODO saving avatar bitmap
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun initUI() {
        profile_vp.adapter = ProfilePagerAdapter(context, this::nextProfilePage, this::showPictureDialog)
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

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(context!!)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(pictureDialogItems) { _, which ->
            when (which) {
                0 -> choosePhotoFromGallery()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    private fun choosePhotoFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)

        //TODO request camera permission
        //startActivityForResult(intent, CAMERA)
    }
}
