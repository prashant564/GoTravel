package com.example.trip_planner_home_page

import android.content.Intent

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_city_detail_page.*

class CityDetailPage : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_detail_page)


//        val extras: Bundle? = intent.extras
//        if(extras!=null){
//            val d_city_name: String? = extras.getString("d_city_destination")
//            val about: String? = extras.getString("d_about")
//            val d_package_name: String? = extras.getString("d_name")
//            val d_t_d: String? = extras.getString("d_t_d")
//            val d_duration: String? = extras.getString("d_duration")
//            val d_price: String? = extras.getString("d_price")
//            val d_information: String? = extras.getString("d_information")



//            textView_about_city.text = "$about"
//            textView_city_name.text = "$d_city_name"
//            button_package.text = "$d_package_name"
//            textView_city_tourist_attraction.text = "$d_t_d"


            button_package.setOnClickListener {

                val intent = Intent(this@CityDetailPage, PackageDetail::class.java)

//                intent.putExtra("d_duration",d_duration)
//                intent.putExtra("d_information",d_information)
//                intent.putExtra("d_name",d_package_name)
//                intent.putExtra("d_price",d_price)
//                intent.putExtra("d_t_d",d_t_d)


                startActivity(intent)
            }


        }

        //val listView_pp: ListView = findViewById(R.id.listView_packages)


        //val adapter_pp = ArrayAdapter(this,R.layout.layout_citypage_tourist_and_package, )


        //listView_pp.setAdapter(adapter_pp)

        //listView_pp.setOnItemClickListener {parent, view, position, id ->


        }


