package com.wiwki.kurinder.data.source.remote

import com.google.firebase.storage.UploadTask

interface FirebaseStorageRemote {

    fun uploadImage(imageName: String, bitmapData: ByteArray): UploadTask
}