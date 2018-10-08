package com.wiwki.kurinder.di.modules

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.wiwki.kurinder.data.source.remote.FirebaseApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
class NetworkModule() {

    private var baseUrl = ""

    constructor(baseUrl: String) : this() {
        this.baseUrl = baseUrl
        Timber.d(baseUrl)
    }

    @Provides
    @Singleton
    fun provideFirebaseApi(): FirebaseApi = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(FirebaseApi::class.java)
}
