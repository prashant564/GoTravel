package com.example.trip_planner_home_page.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CityPackage(val pid: String, val from_city:String, val to_city: String, val city_about: String, val t_a:String, val packageName: String,val duration: String, val price: String, val package_info:String ) : Parcelable {

    constructor(): this("","","","","","","","","")
}