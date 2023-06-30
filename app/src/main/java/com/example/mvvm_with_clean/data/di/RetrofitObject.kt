package com.example.mvvm_with_clean.data.di

import com.example.mvvm_with_clean.data.network.interceptor.LoggingInterceptor
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    inline fun <reified T> createNetworkService(): T {
        val log = LoggingInterceptor().getInterceptor()
        val client = OkHttpClient().newBuilder().addInterceptor(log)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://649daf509bac4a8e669e2717.mockapi.io/")
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        return retrofit.create(T::class.java)
    }
}