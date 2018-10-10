package com.wiwki.kurinder.util

import com.wiwki.kurinder.R

enum class ProfileDetailEnum(
        val titleResId: Int,
        val buttonTextResId: Int
) {
    NAME(R.string.profile_name_title, R.string.continue_str),
    SEX(R.string.profile_sex_title, R.string.continue_str),
    BIRTHDATE(R.string.profile_birthdate_title, R.string.continue_str),
    DESCRIPTION(R.string.profile_desc_title, R.string.continue_str),
    AVATAR(R.string.profile_avatar_title, R.string.done)
}