package com.wiwki.kurinder.data.repositories

import com.google.firebase.storage.UploadTask
import com.wiwki.kurinder.data.source.remote.FirebaseStorageRemote
import javax.inject.Inject

class UserRepository @Inject constructor(private val firebaseStorageRemote: FirebaseStorageRemote) {

    fun uploadImage(imageName: String, bitmapData: ByteArray): UploadTask =
            firebaseStorageRemote.uploadImage(imageName, bitmapData)


}