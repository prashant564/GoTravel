package com.example.trip_planner_home_page

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Your_Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your__profile)

        supportActionBar?.title = "Your Profile"
    }
}
