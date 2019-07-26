package com.example.trip_planner_home_page.models

class CityPackage(val pid: String, val from_city:String, val to_city: String, val city_about: String, val t_a:String, val PackageName: String,val Duration: String, val price: String, val package_info:String ) {

    constructor(): this("","","","","","","","","")
}