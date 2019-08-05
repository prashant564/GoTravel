package com.example.trip_planner_home_page

import android.content.Intent
import android.content.pm.PackageItemInfo

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trip_planner_home_page.models.Cities
import com.example.trip_planner_home_page.models.CityPackage
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_city_detail_page.*
import kotlinx.android.synthetic.main.pacakge_info_row.view.*

class CityDetailPage : AppCompatActivity() {

    val i = Int
    var from_city = ArrayList<String>()
    var to_city = ArrayList<String>()
    var about_city = ArrayList<String>()
    var packageName = ArrayList<String>()
    var duration = ArrayList<String>()
    var  price = ArrayList<String>()
    var package_info = ArrayList<String>()
    var  t_a = ArrayList<String>()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_detail_page)
        val destCityItem: Cities = intent.getParcelableExtra(DestinationCity.DEST_USER_KEY)
        supportActionBar?.title = destCityItem.city_name

        fetchDestinationCityDetails()

//        supportActionBar?.title = city_detail.to_city
//        Picasso.get().load(cities.city_image_url).into(imageView_city_image)
//        textView_about_city.text = city_detail.city_about
//        textView_city_tourist_attraction.text = city_detail.t_a
//        counter = counter + 1
//        Toast.makeText(baseContext,counter,Toast.LENGTH_SHORT)


    }


    companion object{

        val CITY_USER_KEY = "CITY_USER_KEY"
    }

    private fun fetchDestinationCityDetails(){

        val ref = FirebaseDatabase.getInstance().getReference("/CityPackage")

        ref.addValueEventListener(object: ValueEventListener{



            override fun onDataChange(p0: DataSnapshot) {

                val destCityItem: Cities = intent.getParcelableExtra(DestinationCity.DEST_USER_KEY)
                val adapter = GroupAdapter<ViewHolder>()
                val extras: Bundle? = intent.extras
                val curr_city: String? = extras!!.getString("curr_city")
                var counter = 0

                p0.children.forEach {

                    val city_detail = it.getValue(CityPackage::class.java)
                    if(city_detail!=null){

                        from_city.add(city_detail.from_city)
                        to_city.add(city_detail.to_city)
                        about_city.add(city_detail.city_about)
                        t_a.add(city_detail.t_a)
                        packageName.add(city_detail.packageName)
                        duration.add(city_detail.Duration)
                        price.add(city_detail.price)
                        package_info.add(city_detail.package_info)
                        counter = counter + 1

                    }
                }

                for(value in packageName){
                    Log.d("CityDetail",value)
                }

                for(i in 0..counter-1){

                    if(from_city[i]==curr_city && to_city[i]==destCityItem.city_name){

                        Picasso.get().load(destCityItem.city_image_url).into(imageView_city_image)
                        textView_about_city.text = about_city[i]
                        textView_city_tourist_attraction.text = t_a[i]
                        Log.d("CityDetail",packageName[i])
                            adapter.add(PackageNameItem(packageName[i]))
                    }


                }

             adapter.setOnItemClickListener {item, view ->

                 val packageNameItem = item as PackageNameItem
                 val intent = Intent(this@CityDetailPage,PackageDetail::class.java)
                 intent.putExtra("package name",packageNameItem.name)
                 startActivity(intent)
             }

                recyclerView_listOfPackages.adapter = adapter

            }


            override fun onCancelled(p0: DatabaseError) {

            }

        })



    }
    class PackageNameItem(val name: String): Item<ViewHolder>() {

        override fun bind(viewHolder: ViewHolder, position: Int) {

            viewHolder.itemView.button_package_name_recyclerview.text = name

        }

        override fun getLayout(): Int {
            return R.layout.pacakge_info_row
        }
    }

}


