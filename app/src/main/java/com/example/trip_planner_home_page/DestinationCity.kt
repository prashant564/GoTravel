package com.example.trip_planner_home_page

import android.content.Intent
import android.os.AsyncTask

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trip_planner_home_page.models.Cities
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_destination_city.*
import kotlinx.android.synthetic.main.destination_city_row.view.*
import java.util.ArrayList

class DestinationCity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_city)

        supportActionBar?.title = "Select Destination City"




        fetchDestinationCity()

    }

    companion object{

        val DEST_USER_KEY = "DEST_USER_KEY"
    }


    private fun fetchDestinationCity(){
        val ref = FirebaseDatabase.getInstance().getReference("/cityInformation")

        ref.addListenerForSingleValueEvent(object: ValueEventListener{

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()
                val cities: Cities = intent.getParcelableExtra(MainActivity.USER_KEY)
                val curr_city: String = cities.city_name
                p0.children.forEach {
                    val destinationCity = it.getValue(Cities::class.java)

                    if(destinationCity!=null){
                        if(cities.city_name!=destinationCity.city_name){
                            adapter.add(DestCityItem(destinationCity))
                        }else{
//                            Toast.makeText(baseContext,"Choose another city",Toast.LENGTH_SHORT).show()
                        }

                    }
                }

                adapter.setOnItemClickListener {item, view ->

                    val destCityItem = item as DestCityItem

                    val intent = Intent(view.context,CityDetailPage::class.java)
                    intent.putExtra("DEST_USER_KEY",destCityItem.cities)
                    intent.putExtra("curr_city",curr_city)


                    startActivity(intent)

                    finish()

                }
                recyclerView_destination_city.adapter = adapter


            }
        })

    }

    class DestCityItem(val cities: Cities): Item<ViewHolder>(){

        override fun bind(viewHolder: ViewHolder, position: Int) {

            viewHolder.itemView.textView_destination_city.text = cities.city_name

        }

        override fun getLayout(): Int {
            return R.layout.destination_city_row
        }
    }
}








