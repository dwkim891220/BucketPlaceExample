package com.example.dwkim

import android.app.Application
import com.example.dwkim.util.di.appModules
import org.koin.android.ext.android.startKoin

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, appModules)
    }
}