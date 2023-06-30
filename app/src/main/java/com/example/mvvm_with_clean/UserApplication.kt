package com.example.mvvm_with_clean

import android.app.Application
import org.koin.core.context.startKoin

class UserApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setUpKoin()
    }

    private fun setUpKoin() {
        startKoin {
            modules(modules)
        }
    }
}