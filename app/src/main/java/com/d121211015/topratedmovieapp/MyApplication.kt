package com.d121211015.topratedmovieapp

import android.app.Application
import com.d121211015.topratedmovieapp.data.AppContainer
import com.d121211015.topratedmovieapp.data.DefaultAppContainer

class MyApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}