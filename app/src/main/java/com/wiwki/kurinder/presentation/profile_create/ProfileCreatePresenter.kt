package com.wiwki.kurinder.presentation.profile_create

import android.graphics.Bitmap
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.wiwki.kurinder.data.entities.User
import ru.terrakok.cicerone.Router
import java.util.*
import javax.inject.Inject

@InjectViewState
class ProfileCreatePresenter @Inject constructor(
        private val router: Router
) : MvpPresenter<ProfileCreateView>() {

    private val user by lazy { User() }

    fun navigateToPrevScreen() {
        router.exit()
    }

    fun setName(name: String) {
        user.name = name
    }

    fun setMale(isMale: Boolean) {
        user.isMale = isMale
    }

    fun setBirthDate(date: Date) {
        user.birthDate = date.time
    }

    fun setDescription(desc: String) {
        user.desc = desc
    }

    fun loadAndSetImageUrl(image: Bitmap) {

    }
}