package com.example.trip_planner_home_page

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_rate_us.*

class RateUs : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_us)
        supportActionBar?.title = "Rate Us"

     radioGroup_1.setOnCheckedChangeListener{ group, checkedId ->
         startActivity(Intent(this@RateUs, rateus_display_message::class.java))
         overridePendingTransition(R.anim.from_left_out,R.anim.from_right_in)
     }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.from_left_in,R.anim.from_right_out)
    }

}

