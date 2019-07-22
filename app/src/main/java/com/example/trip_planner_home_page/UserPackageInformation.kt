package com.example.trip_planner_home_page

import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_user_package_information.*

class UserPackageInformation : AppCompatActivity() {

    val counterValue = null
    var counter: Int = 0
    var tripDate: String = null.toString()
    var flag: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_package_information)

//        val extras: Bundle? = intent.extras

        textView_counter_adult.setText("0")

        button_add_adult.setOnClickListener{
            counter++
            textView_counter_adult.text = "$counter"
        }

        button_subtract_adult.setOnClickListener {

            if (counter > 0) {
                counter--
                textView_counter_adult.text = "$counter"
            }
        }

        calendarView_set_date.setOnDateChangeListener {view, year, month, dayOfMonth ->

            flag = 1
            tripDate =  "$dayOfMonth" + "-"  + (month+1) + "-" + year
            Toast.makeText(this@UserPackageInformation, tripDate, Toast.LENGTH_SHORT).show()

        }

//            val d_name: String? = extras!!.getString("d_name")
//            textView_final_package_name.text = "$d_name"
//            val d_price: String? = extras!!.getString("d_price")
//


            button_next.setOnClickListener {


                val intent = Intent(this@UserPackageInformation, PackageSummary::class.java)

//                intent.putExtra("d_name", d_name)


                var counterValue = textView_counter_adult.text
                intent.putExtra("counterValue", counterValue)
//                intent.putExtra("d_price",d_price)
                intent.putExtra("trip_date",tripDate)

                if(flag==1){
                    startActivity(intent)
                }
                else
                {
                    Toast.makeText(this@UserPackageInformation, "Please select the date", Toast.LENGTH_SHORT).show()
                }


            }


            }

        }














