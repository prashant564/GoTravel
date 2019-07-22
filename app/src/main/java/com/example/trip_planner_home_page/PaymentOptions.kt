package com.example.trip_planner_home_page

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_payment_options.*

class PaymentOptions : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_options)

        button_proceed.setOnClickListener { startActivity(Intent(this@PaymentOptions, BookingSuccessful::class.java)) }
    }
}
