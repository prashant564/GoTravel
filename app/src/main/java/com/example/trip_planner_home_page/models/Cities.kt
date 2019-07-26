package com.example.trip_planner_home_page.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Cities(val city_id: String,val city_name:String, val city_image_url: String, val image_1:String,val image_2:String,val image_3:String,val image_4: String): Parcelable{
    constructor(): this("","","","","","","")

}