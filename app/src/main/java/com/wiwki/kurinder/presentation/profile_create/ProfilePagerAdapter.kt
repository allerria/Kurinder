package com.wiwki.kurinder.presentation.profile_create

import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import android.support.v4.view.PagerAdapter
import android.view.View
import com.wiwki.kurinder.R
import com.wiwki.kurinder.util.ProfileDetailEnum
import kotlinx.android.synthetic.main.item_profile_create.view.*

class ProfilePagerAdapter(
        private val mContext: Context?,
        private val callbackNextPage: (ProfileDetailEnum, Any) -> Unit
) : PagerAdapter() {

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

    private fun initView(layout: ViewGroup, profileDetailEnum: ProfileDetailEnum) {
        with(layout) {
            profile_create_tv.text = context.getString(profileDetailEnum.titleResId)
            profile_done_tv.text = context.getString(profileDetailEnum.buttonTextResId)
            profile_done_tv.setOnClickListener {
                callbackNextPage(profileDetailEnum, 1)
            }
            when (profileDetailEnum) {
                com.wiwki.kurinder.util.ProfileDetailEnum.NAME -> profile_name_et
                ProfileDetailEnum.SEX -> profile_sex_rg
                ProfileDetailEnum.BIRTHDATE -> profile_birthdate_et
                ProfileDetailEnum.DESCRIPTION -> profile_desc_et
                ProfileDetailEnum.AVATAR -> profile_avatar_iv
            }.visibility = View.VISIBLE
        }
    }
}