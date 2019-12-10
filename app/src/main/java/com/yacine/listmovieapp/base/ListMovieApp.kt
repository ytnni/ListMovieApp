package com.yacine.listmovieapp.base

import android.app.Application
import com.yacine.listmovieapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class ListMovieApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ListMovieApp)
            modules(listOf(networkModule,scopesModule))
        }
    }
}