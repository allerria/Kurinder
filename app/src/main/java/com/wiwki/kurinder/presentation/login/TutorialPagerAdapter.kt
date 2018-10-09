package com.wiwki.kurinder.presentation.login

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.ViewGroup
import android.view.LayoutInflater
import android.support.v4.view.PagerAdapter
import android.view.View
import com.wiwki.kurinder.R
import com.wiwki.kurinder.util.TutorialPagerEnum
import kotlinx.android.synthetic.main.item_tutorial.view.*

class TutorialPagerAdapter(private val mContext: Context?) : PagerAdapter() {

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(mContext)
        val layout = inflater.inflate(R.layout.item_tutorial, collection, false) as ViewGroup
        with(layout) {
            tutorial_tv.text = TutorialPagerEnum.values()[position].text
            tutorial_iv.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.common_google_signin_btn_icon_dark))
        }
        collection.addView(layout)
        return layout
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int = TutorialPagerEnum.values().size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`
}