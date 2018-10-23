package com.wiwki.kurinder.presentation.profile_create

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.wiwki.kurinder.R
import com.wiwki.kurinder.presentation.Screens
import com.wiwki.kurinder.presentation.common.BaseFragment
import com.wiwki.kurinder.util.ProfileDetailEnum
import timber.log.Timber
import javax.inject.Inject
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.fragment_create_profile.*
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch
import java.lang.RuntimeException
import java.util.*


class ProfileCreateFragment : BaseFragment(), ProfileCreateView {

    override val layoutRes: Int = R.layout.fragment_create_profile
    override val TAG: String = Screens.PROFILE_CREATE_SCREEN

    private val GALLERY = 1
    private val CAMERA = 2
    private val DATA = "data"
    private val vpAdapter by lazy {
        ProfilePagerAdapter(context, this::nextProfilePage,
                this::requestPermissionAndShowAvatarDialog)
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

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
        data?.let {
            val bitmap: Bitmap = when (requestCode) {
                GALLERY -> MediaStore.Images.Media.getBitmap(activity?.contentResolver, it.data)
                CAMERA -> it.extras.get(DATA) as Bitmap
                else -> throw RuntimeException("not expected requestCode")
            }
            vpAdapter.setAvatar(bitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun showError(e: Exception) {
        Timber.e(e)
        Toast.makeText(context, "asdsad", Toast.LENGTH_SHORT).show()
        stopLoadAnimation()
    }

    override fun showLoadAnimation() {
        profile_cl.visibility = View.GONE
        profile_load_anim.visibility = View.VISIBLE
    }

    override fun stopLoadAnimation() {
        profile_cl.visibility = View.VISIBLE
        profile_load_anim.visibility = View.GONE
    }

    private fun initUI() {
        profile_vp.adapter = vpAdapter
        profile_vp.offscreenPageLimit = vpAdapter.count
        profile_tl.setupWithViewPager(profile_vp)
    }

    private fun nextProfilePage(dataKey: ProfileDetailEnum, data: Any) {
        when (dataKey) {
            ProfileDetailEnum.NAME -> {
                presenter.setName(data as String)
            }
            ProfileDetailEnum.SEX -> {
                presenter.setMale(data as Boolean)
            }
            ProfileDetailEnum.BIRTHDATE -> {
                presenter.setBirthDate(data as Date)
            }
            ProfileDetailEnum.DESCRIPTION -> {
                presenter.setDescription(data as String)
            }
            ProfileDetailEnum.AVATAR -> {
                presenter.loadAndSetImageUrl(data as Bitmap)
            }
        }
        if (profile_vp.currentItem == vpAdapter.count - 1) {
            return
        }
        profile_vp.getChildAt(profile_vp.currentItem).visibility = View.GONE
        profile_vp.currentItem++
        profile_vp.getChildAt(profile_vp.currentItem).visibility = View.VISIBLE
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

    private fun requestPermissionAndShowAvatarDialog() {
        Dexter.withActivity(activity)
                .withPermissions(listOf(Manifest.permission.CAMERA, Manifest.permission
                        .READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION))
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>?, token: PermissionToken?) {
                        token?.continuePermissionRequest()
                    }

                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        GlobalScope.launch {
                            getLastLocation()
                        }
                        showPictureDialog()
                    }
                }).check()
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
        startActivityForResult(intent, CAMERA)
    }

    @SuppressLint("MissingPermission")
    suspend private fun getLastLocation() {
        activity?.let { activity ->
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    presenter.setLocation(it)
                }
            }
        }
    }
}
