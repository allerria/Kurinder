package com.wiwki.kurinder.presentation.profile_create

import android.graphics.Bitmap
import android.location.Location
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.wiwki.kurinder.data.entities.User
import com.wiwki.kurinder.domain.UserInteractor
import com.wiwki.kurinder.presentation.Screens
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch
import ru.terrakok.cicerone.Router
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@InjectViewState
class ProfileCreatePresenter @Inject constructor(
        private val router: Router,
        private val userInteractor: UserInteractor
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
        user.birthDate = date
    }

    fun setDescription(desc: String) {
        user.desc = desc
    }

    fun setLocation(location: Location) {
        user.geolocation = Pair(location.latitude, location.longitude)
    }

    fun loadAndSetImageUrl(image: Bitmap) {
        viewState.showLoadAnimation()
        GlobalScope.launch(Dispatchers.Main) {
            userInteractor.uploadImage(image, this@ProfileCreatePresenter::createUser, viewState::showError)
        }
    }

    private fun createUser(url: String) {
        Timber.d("GOOD")
        user.imageUrl = url
        Timber.d(user.toString())
        viewState.showLoadAnimation()
        GlobalScope.launch(Dispatchers.Main) {
            userInteractor.uploadUser(user, this@ProfileCreatePresenter::onSuccessUserLoaded, viewState::showError)
        }
    }

    private fun onSuccessUserLoaded() {
        viewState.stopLoadAnimation()
        router.navigateTo(Screens.FeedScreen())
    }
}