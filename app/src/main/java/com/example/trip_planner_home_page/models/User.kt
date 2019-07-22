package com.example.trip_planner_home_page.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(val uid: String,val username: String,val image_url: String) : Parcelable {
    constructor(): this("","","")

}