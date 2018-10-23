package com.wiwki.kurinder.domain

import android.graphics.Bitmap
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.storage.FirebaseStorage
import com.wiwki.kurinder.data.entities.User
import kotlinx.coroutines.experimental.*
import java.io.ByteArrayOutputStream
import java.lang.RuntimeException
import javax.inject.Inject

class UserInteractor @Inject constructor(
        private val authInteractor: AuthInteractor
) {
    private val storage by lazy { FirebaseStorage.getInstance() }
    private val fireStore by lazy { FirebaseFirestore.getInstance() }
    private val USERS_COLLECTION: String = "users"

    suspend fun uploadImage(bitmap: Bitmap, successCallback: (String) -> Unit, errorCallback: (e: Exception) -> Unit) {
        val avatarRef = storage.reference.child("images/${authInteractor.getId()}")
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, baos)
        val data = baos.toByteArray()
        avatarRef.putBytes(data).addOnSuccessListener { it ->
            avatarRef.downloadUrl
                    .addOnSuccessListener { successCallback(it.toString()) }
                    .addOnCanceledListener { errorCallback(RuntimeException("AVATAR PROEBALSYA")) }
        }.addOnCanceledListener {
            errorCallback(RuntimeException("AVATAR PROEBALSYA"))
        }
    }

    suspend fun uploadUser(user: User, successCallback: () -> Unit, errorCallback: (e: Exception) -> Unit) {
        val users = fireStore.collection(USERS_COLLECTION)
        val document = users.document(authInteractor.getId())

        val u = mutableMapOf<String, Any>()
        u.put("birthdate", user.birthDate)
        u.put("desc", user.desc)
        u.put("geolocation", GeoPoint(user.geolocation.first, user.geolocation.second))
        u.put("image_url", user.imageUrl)
        u.put("is_male", user.isMale)
        u.put("name", user.name)

        document.set(u)
                .addOnSuccessListener { successCallback() }
                .addOnFailureListener { errorCallback(it) }
    }
}