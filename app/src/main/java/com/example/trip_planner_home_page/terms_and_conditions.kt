package com.example.trip_planner_home_page

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class terms_and_conditions : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_conditions)
        supportActionBar?.title = "Terms and Conditions"
    }


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.from_left_in,R.anim.from_right_out)
    }

}
