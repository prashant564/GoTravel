package com.example.trip_planner_home_page

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Contact_Us : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact__us)
        supportActionBar?.title = "Contact Us"
    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.from_left_in,R.anim.from_right_out)
    }

}
