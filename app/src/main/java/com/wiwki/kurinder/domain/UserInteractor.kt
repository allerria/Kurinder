package com.wiwki.kurinder.domain

import com.google.firebase.storage.UploadTask
import com.wiwki.kurinder.data.repositories.UserRepository
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository: UserRepository) {

    fun uploadImage(imageName: String, bitmapData: ByteArray): UploadTask =
            userRepository.uploadImage(imageName, bitmapData)
}