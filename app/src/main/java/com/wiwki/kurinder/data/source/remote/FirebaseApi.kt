package com.wiwki.kurinder.data.source.remote

import okhttp3.Response
import retrofit2.http.GET

interface FirebaseApi {

    @GET
    fun dosmth(): Response

}