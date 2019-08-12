package com.example.trip_planner_home_page

import android.content.Intent
import android.os.AsyncTask

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.example.trip_planner_home_page.models.Cities
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_destination_city.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.destination_city_row.view.*
import kotlinx.android.synthetic.main.destination_snapshot_images.view.*
import java.util.ArrayList

class DestinationCity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_city)

        supportActionBar?.title = "Select Destination City"
        fetchDestinationCity()

        recyclerView_destination_city_snapshot_images.layoutManager = LinearLayoutManager(this,OrientationHelper.HORIZONTAL,false)
    }

    companion object{

        val DEST_USER_KEY = "DEST_USER_KEY"
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){

            R.id.menu_home -> {

                val intent = Intent(this, MainActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }

            R.id.menu_sign_out -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, RegisterActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun fetchDestinationCity(){
        val ref = FirebaseDatabase.getInstance().getReference("/cityInformation")

        ref.addListenerForSingleValueEvent(object: ValueEventListener{

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()
                val adapter_dest_images_snapshot = GroupAdapter<ViewHolder>()
                val cities: Cities = intent.getParcelableExtra(MainActivity.USER_KEY)
                val curr_city: String = cities.city_name
                p0.children.forEach {
                    val destinationCity = it.getValue(Cities::class.java)

                    if(destinationCity!=null){
                        if(cities.city_name!=destinationCity.city_name){
                            adapter.add(DestCityItem(destinationCity))
                            adapter_dest_images_snapshot.add(destimagesSnapshotItem(cities.image_3))
                            adapter_dest_images_snapshot.add(destimagesSnapshotItem(cities.image_2))
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
                recyclerView_destination_city.addItemDecoration(DividerItemDecoration(this@DestinationCity, DividerItemDecoration.VERTICAL))
                recyclerView_destination_city_snapshot_images.adapter = adapter_dest_images_snapshot
                recyclerView_destination_city_snapshot_images.addItemDecoration(DividerItemDecoration(this@DestinationCity, DividerItemDecoration.HORIZONTAL))



            }
        })

    }

    class DestCityItem(val cities: Cities): Item<ViewHolder>(){

        override fun bind(viewHolder: ViewHolder, position: Int) {

            Picasso.get().load(cities.city_image_url).into(viewHolder.itemView.imageView_city_image_dest_city)
            viewHolder.itemView.textView_dest_city.text = cities.city_name

        }

        override fun getLayout(): Int {
            return R.layout.destination_city_row
        }
    }

    class destimagesSnapshotItem(val url:String): Item<ViewHolder>(){

        override fun bind(viewHolder: ViewHolder, position: Int) {

            Picasso.get().load(url).into(viewHolder.itemView.imageView_dest_snapshot_images)

        }

        override fun getLayout(): Int {

            return R.layout.destination_snapshot_images
        }
    }
}








