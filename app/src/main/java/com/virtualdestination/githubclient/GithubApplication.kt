package com.virtualdestination.githubclient

import android.app.Application
import android.content.Context

/**
 * Created by Rashid on 26/11/2017.
 */
class GithubApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: GithubApplication? = null
            private set
        val context: Context
            get() = instance!!.applicationContext
    }
}