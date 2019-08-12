package com.example.trip_planner_home_page

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.trip_planner_home_page.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_your__profile.*

class Your_Profile : AppCompatActivity() {


    companion object{

        var currentUser: User? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your__profile)

        supportActionBar?.title = "Your Profile"

        displayProfileInformation()

    }




    private fun displayProfileInformation(){

        val uid = FirebaseAuth.getInstance().uid

        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        ref.addListenerForSingleValueEvent(object: ValueEventListener{

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {

                p0.children.forEach{
                    val user = it.getValue(User::class.java)

                    if(user?.uid == uid){

                        textView_username.text = user?.username
                        textView_login_email.text = user?.user_email
//                textView_city.text =
                        Picasso.get().load(user?.image_url).into(imageView_profile_pic)

                    }

                }

            }
        })
    }
}
