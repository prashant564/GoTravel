package com.example.trip_planner_home_page

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_package_detail.*

class PackageDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package_detail)

//        val extras: Bundle? = intent.extras
//        if(extras!=null){
//            val d_duration: String? = extras.getString("d_duration")
//            val d_price: String? = extras.getString("d_price")
//            val d_t_d: String? = extras.getString("d_t_d")
//            val d_name: String? = extras.getString("d_name")
//            val d_information: String? = extras.getString("d_information")


//            textView_final_package_name.text = "$d_name"
//            textView_duration_value.text = "$d_duration"
//            textView_price_value.text = "$d_price"
//            textView_package_t_d.text = "$d_t_d"
//            textView_package_info.text = "$d_information"

            button_book.setOnClickListener{
                val intent = Intent(this@PackageDetail, UserPackageInformation::class.java)
//                intent.putExtra("d_name",d_name)
//                intent.putExtra("d_price",d_price)
                startActivity(intent)
            }
        }



    }

