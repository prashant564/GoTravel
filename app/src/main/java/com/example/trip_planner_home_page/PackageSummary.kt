package com.example.trip_planner_home_page

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_package_summary.*

class PackageSummary : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package_summary)

//        val extras :Bundle? = intent.extras
//        if(extras !=null){
//
//            val value: String? = intent.getStringExtra("counterValue")
//            textView_number_of_people.text = "Number of people: $value"
//
//            val d_name: String? = intent.getStringExtra("d_name")
//            val d_price: String? = intent.getStringExtra("d_price")
//            val tripDate: String? = intent.getStringExtra("trip_date")
//
//            val price: Int = value!!.toInt() * d_price!!.toInt()
//
//
//            textView_final_package_name.text = "$d_name"
//            textView_total_price.text = "$price"
//            textView_date.text = "$tripDate"
//        }



        button_pay_now.setOnClickListener { startActivity(Intent(this@PackageSummary, PaymentOptions::class.java)) }
    }
}
