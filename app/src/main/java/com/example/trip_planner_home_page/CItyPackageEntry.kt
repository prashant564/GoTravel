package com.example.trip_planner_home_page

import android.app.AppComponentFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.trip_planner_home_page.models.CityPackage
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.entry.*
import java.util.*

class CItyPackageEntry : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entry)

        button_saveentry.setOnClickListener {
            storeCityPackageToFirebaseDatabase()
        }
        storeCityPackageToFirebaseDatabase()
    }

    private fun storeCityPackageToFirebaseDatabase(){

        val uid =  UUID.randomUUID().toString()
        val ref = FirebaseDatabase.getInstance().getReference("/CityPackage").push()

      val cityPackage = CityPackage(uid,"MUMBAI","MANALI","Manali is a high-altitude Himalayan resort town in India’s northern Himachal Pradesh state. It has a reputation as a backpacking center and honeymoon destination. Set on the Beas River, it’s a gateway for skiing in the Solang Valley and trekking in Parvati Valley. It's also a jumping-off point for paragliding, rafting and mountaineering in the Pir Panjal mountains, home to 4,000m-high Rohtang Pass.",
          "HIDIMBA DEVI TEMPLE ,MANU TEMPLE,MANALI SANCTUARY, MALL ROAD, SOLANG VALLEY","MUMMAN-PREMIUM PACKAGE",
          "5NIGHTS/4DAYS","15000","Pick this holiday and head for a memorable vacation to the beautiful and serene Manali. This tour not only lets you embrace nature in all its glory but also brings out the adventurer in you. A 14-hour road trip from Delhi will transport you to Manali, wherein the majestic mountains and lush green landscape will leave you spellbound. When in Manali, explore the quaint town, visit Solang Valley and enjoy local delicacies. This trip into nature's arms is going to be an unforgettable experience.")
         ref.setValue(cityPackage)
             .addOnSuccessListener {
                 Log.d("CityPackageEntry","Entry Successful")
             }



    }

}