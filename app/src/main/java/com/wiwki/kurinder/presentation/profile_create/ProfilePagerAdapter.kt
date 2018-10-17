package com.wiwki.kurinder.presentation.profile_create

import android.content.Context
import android.graphics.Bitmap
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.viewpager.widget.PagerAdapter
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.tsongkha.spinnerdatepicker.DatePicker
import com.tsongkha.spinnerdatepicker.DatePickerDialog
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder
import com.wiwki.kurinder.R
import com.wiwki.kurinder.util.ProfileDetailEnum
import kotlinx.android.synthetic.main.item_profile_create.view.*
import java.util.*

class ProfilePagerAdapter(
        private val mContext: Context?,
        private val callbackNextPage: (ProfileDetailEnum, Any) -> Unit,
        private val callbackPickAvatar: () -> Unit
) : PagerAdapter(), DatePickerDialog.OnDateSetListener {

    private var tempData: Any? = null
    private var tempDate: Calendar? = null
    private val defaultDate: Calendar = Calendar.getInstance()
    private var tempAvatar: Bitmap? = null
    private lateinit var birthDayEditText: EditText
    private lateinit var profileAvatarIV: ImageView

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(mContext)
        val layout = inflater.inflate(R.layout.item_profile_create, collection, false) as ViewGroup
        val profileDetailEnum = ProfileDetailEnum.values()[position]
        initView(layout, profileDetailEnum)
        collection.addView(layout)
        return layout
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int = ProfileDetailEnum.values().size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`

    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, monthOfYear, dayOfMonth)
        tempDate = calendar
        showBirthday(year, monthOfYear, dayOfMonth)
    }

    private fun initView(layout: ViewGroup, profileDetailEnum: ProfileDetailEnum) {
        with(layout) {
            profile_create_tv.text = context.getString(profileDetailEnum.titleResId)
            profile_done_tv.text = context.getString(profileDetailEnum.buttonTextResId)
            profile_done_tv.setOnClickListener {
                updateFieldTempData(profileDetailEnum, layout)
                tempData?.let { data ->
                    callbackNextPage(profileDetailEnum, data)
                }
            }
            when (profileDetailEnum) {
                ProfileDetailEnum.NAME -> profile_name_et
                ProfileDetailEnum.SEX -> profile_sex_rg
                ProfileDetailEnum.BIRTHDATE -> {
                    birthDayEditText = profile_birthdate_et
                    profile_birthdate_et.setOnClickListener {
                        showBirthdayPicker(context)
                    }
                    profile_birthdate_et
                }
                ProfileDetailEnum.DESCRIPTION -> profile_desc_et
                ProfileDetailEnum.AVATAR -> {
                    profile_avatar_iv.setOnClickListener {
                        callbackPickAvatar()
                    }
                    profileAvatarIV = profile_avatar_iv
                    profile_avatar_iv
                }
            }.visibility = View.VISIBLE
        }
    }

    private fun updateFieldTempData(profileDetailEnum: ProfileDetailEnum, layout: ViewGroup) {
        with(layout) {
            tempData = when (profileDetailEnum) {
                ProfileDetailEnum.NAME ->
                    if (profile_name_et.text.isNotEmpty()) {
                        profile_name_et.text
                    } else {
                        profile_name_et.error = context.getString(R.string.field_empty)
                        null
                    }
                ProfileDetailEnum.SEX -> male_rb.isChecked
                ProfileDetailEnum.BIRTHDATE -> if (tempDate != null) {
                    tempDate
                } else {
                    profile_birthdate_et.error = context.getString(R.string.field_empty)
                    null
                }
                ProfileDetailEnum.DESCRIPTION ->
                    if (profile_desc_et.text.isNotEmpty()) {
                        profile_desc_et.text
                    } else {
                        profile_desc_et.error = context.getString(R.string.field_empty)
                        null
                    }
                ProfileDetailEnum.AVATAR ->
                    if (tempAvatar != null) {

                    } else {
                        showNoAvatarError(context)
                        null
                    }
            }
        }
    }

    private fun showBirthdayPicker(context: Context) {
        SpinnerDatePickerDialogBuilder()
                .context(context)
                .callback(this@ProfilePagerAdapter)
                .spinnerTheme(R.style.AppTheme)
                .defaultDate(defaultDate.get(Calendar.YEAR), defaultDate.get(Calendar.MONTH),
                        defaultDate.get(Calendar.DAY_OF_MONTH))
                .build()
                .show()
    }

    private fun showBirthday(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        birthDayEditText.setText("$dayOfMonth/$monthOfYear/$year")
    }

    private fun showNoAvatarError(context: Context) {
        Toast.makeText(context, context.getString(R.string.load_avatar), Toast.LENGTH_SHORT).show()
    }

    fun setAvatar(bitmap: Bitmap) {
        tempAvatar = bitmap
        profileAvatarIV.setImageBitmap(bitmap)
    }
}