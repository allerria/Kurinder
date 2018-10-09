package com.wiwki.kurinder.data.source.remote

import com.google.firebase.storage.UploadTask
import okhttp3.Response
import retrofit2.http.GET

interface FirebaseStorageRemote {

    fun uploadImage(imageName: String, bitmapData: ByteArray): UploadTask

}