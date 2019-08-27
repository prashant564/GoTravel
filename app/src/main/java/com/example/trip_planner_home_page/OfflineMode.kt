package com.example.trip_planner_home_page

import android.app.Application
import com.google.firebase.database.FirebaseDatabase
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient




class OfflineMode: Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        //for saving images

        val client = OkHttpClient()
        val picasso = Picasso.Builder(this).downloader(OkHttp3Downloader(client)).build()
//        picasso.setIndicatorsEnabled(false)
    }


}
