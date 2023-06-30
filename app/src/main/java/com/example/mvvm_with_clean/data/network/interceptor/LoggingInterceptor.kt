package com.example.mvvm_with_clean.data.network.interceptor

import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig

class LoggingInterceptor {
    fun getInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
        }
    }
}