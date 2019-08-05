package com.example.trip_planner_home_page

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.trip_planner_home_page.models.CityPackage
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_package_detail.*

class PackageDetail : AppCompatActivity() {



    var package_name:String = " "
    var package_price: String = " "


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package_detail)

        fetchPackageDetails()

        button_book.setOnClickListener {

            val intent = Intent(this@PackageDetail,UserPackageInformation::class.java)
            intent.putExtra("package name",package_name)
            intent.putExtra("package price",package_price)
            startActivity(intent)
        }
    }

    private fun fetchPackageDetails(){
        val ref = FirebaseDatabase.getInstance().getReference("/CityPackage")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {

                val extras: Bundle? = intent.extras
                if(extras!=null){
                    package_name = extras.getString("package name")
                }


                p0.children.forEach {

                    val package_detail = it.getValue(CityPackage::class.java)

                    if(package_detail!=null){

                        if(package_detail.packageName==package_name){

                            package_price = package_detail.price
                            textView_final_package_name.text = package_detail.packageName
                            textView_duration_value.text = package_detail.duration
                            textView_price_value.text = package_detail.price
                            textView_package_t_d.text = package_detail.t_a
                            textView_package_info.text = package_detail.package_info
                        }

                    }

                }

            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })

    }



}




