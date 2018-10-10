package com.wiwki.kurinder.data.repositories

import com.google.firebase.storage.UploadTask
import com.wiwki.kurinder.data.source.remote.FirebaseFirestoreRemote
import com.wiwki.kurinder.data.source.remote.FirebaseStorageRemote
import javax.inject.Inject

class UserRepository @Inject constructor(
        private val firebaseStorageRemote: FirebaseStorageRemote,
        private val firebaseFirestoreRemote: FirebaseFirestoreRemote
) {

    fun uploadImage(imageName: String, bitmapData: ByteArray): UploadTask =
            firebaseStorageRemote.uploadImage(imageName, bitmapData)


}