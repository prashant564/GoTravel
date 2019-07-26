package com.example.trip_planner_home_page

import android.content.Intent
import android.os.AsyncTask

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_destination_city.*
import java.util.ArrayList

class DestinationCity : AppCompatActivity() {

    private val activity = this@DestinationCity




    var values = arrayOf(
        "DELHI","MUMBAI","MANALI")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_city)
        supportActionBar?.title = "Select destination city"

        // initializing the objects


        //picking the data of current city from previous page
//        val extras: Bundle? = intent.extras
//        val city_current:String? = extras!!.getString("city_current")

        //Listview started

        val adapter = ArrayAdapter(
            this,
            R.layout.listview_item, values
        )

        listView_destination_city.setAdapter(adapter)
        listView_destination_city.setOnItemClickListener {parent, view, position, id ->
            startActivity(Intent(this@DestinationCity,CityOnlyEntry::class.java))
        }

            }
//            else{
//                Toast.makeText(this,"Choose another city",Toast.LENGTH_SHORT).show()
//            }





        }








