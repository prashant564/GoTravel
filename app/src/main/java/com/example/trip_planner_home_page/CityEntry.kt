package com.example.trip_planner_home_page

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.example.trip_planner_home_page.models.Cities
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_city_entry.*
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*
import kotlin.collections.ArrayList

class CityEntry : AppCompatActivity() {

    var city_image_url: String? = null
    var image1_url: String? = null
    var image2_url: String? = null
    var image3_url: String? = null
    var image4_url: String? = null

//    val arrayList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_entry)

        button_city_image.setOnClickListener {
            Log.d("CityEntry", "try to show")

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        button_image1.setOnClickListener {

            Log.d("CityEntry","try to show")
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,1)
        }

        button_image2.setOnClickListener {

            Log.d("CityEntry","try to show")
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,2)
        }
        button_image3.setOnClickListener {

            Log.d("CityEntry","try to show")
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,3)
        }

        button_image4.setOnClickListener {

            Log.d("CityEntry","try to show")
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,4)
        }

        button_save_cityinfo.setOnClickListener {
             uploadImagetoFirebaseStorage()


            saveUserToFirebaseDatabase()

        }

//        Log.d("CityEntry",city_image_url)
//        Log.d("CityEntry",image1_url)
//        Log.d("CityEntry",image1_url)

    }

    var selectedPhotoUri0: Uri? = null
    var selectedPhotoUri1: Uri? = null
    var selectedPhotoUri2: Uri? = null
    var selectedPhotoUri3: Uri? = null
    var selectedPhotoUri4: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {

            selectedPhotoUri0 = data.data
            val city_bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri0)

            val bitmapDrawable = BitmapDrawable(city_bitmap)
            button_city_image.setBackgroundDrawable(bitmapDrawable)

        }
        else if(requestCode==1 && resultCode == Activity.RESULT_OK  && data!=null){

            selectedPhotoUri1 = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,selectedPhotoUri1)


            val bitmapDrawable = BitmapDrawable(bitmap)
            button_image1.setBackgroundDrawable(bitmapDrawable)

        }else if(requestCode==2 && resultCode == Activity.RESULT_OK  && data!=null){

            selectedPhotoUri2 = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,selectedPhotoUri2)

            val bitmapDrawable = BitmapDrawable(bitmap)
            button_image2.setBackgroundDrawable(bitmapDrawable)
        }else if(requestCode==3 && resultCode == Activity.RESULT_OK  && data!=null){

            selectedPhotoUri3 = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,selectedPhotoUri3)

            val bitmapDrawable = BitmapDrawable(bitmap)
            button_image3.setBackgroundDrawable(bitmapDrawable)

        }else if(requestCode==4 && resultCode == Activity.RESULT_OK  && data!=null){
            selectedPhotoUri4 = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,selectedPhotoUri4)

            val bitmapDrawable = BitmapDrawable(bitmap)
            button_image4.setBackgroundDrawable(bitmapDrawable)
        } else{
            Toast.makeText(baseContext,"unsuccessful",Toast.LENGTH_SHORT).show()
        }


    }

        private fun uploadImagetoFirebaseStorage() {
            if (selectedPhotoUri0 == null) return
            if (selectedPhotoUri1 == null) return
            if (selectedPhotoUri2 == null) return
            if (selectedPhotoUri3 == null) return
            if (selectedPhotoUri4 == null) return

            val filename0: String = UUID.randomUUID().toString()
            val filename1: String = UUID.randomUUID().toString()
            val filename2: String = UUID.randomUUID().toString()
            val filename3: String = UUID.randomUUID().toString()
            val filename4: String = UUID.randomUUID().toString()

            val ref0 = FirebaseStorage.getInstance().getReference("/Cityimages/$filename0")
            val ref1 = FirebaseStorage.getInstance().getReference("/Cityimages/$filename1")
            val ref2 = FirebaseStorage.getInstance().getReference("/Cityimages/$filename2")
            val ref3 = FirebaseStorage.getInstance().getReference("/Cityimages/$filename3")
            val ref4 = FirebaseStorage.getInstance().getReference("/Cityimages/$filename4")

            ref0.putFile(selectedPhotoUri0!!)
            ref1.putFile(selectedPhotoUri1!!)
            ref2.putFile(selectedPhotoUri2!!)
            ref3.putFile(selectedPhotoUri3!!)
            ref4.putFile(selectedPhotoUri4!!)

                .addOnSuccessListener {

                    val arrayList = ArrayList<String>()

                    ref0.downloadUrl.addOnSuccessListener {

                        city_image_url = it.toString()
                        arrayList.add(it.toString())
                        Log.d("CityEntry",city_image_url)

                    }
                    ref1.downloadUrl.addOnSuccessListener {

                        image1_url = it.toString()
                        arrayList.add(it.toString())
                        Log.d("CityEntry",image1_url)

//                    saveUserToFirebaseDatabase(it.toString())

                    }
                    ref2.downloadUrl.addOnSuccessListener {

                        image2_url = it.toString()
                        arrayList.add(it.toString())
                        Log.d("CityEntry",image2_url)


//                    saveUserToFirebaseDatabase(it.toString())

                    }
                    ref3.downloadUrl.addOnSuccessListener {


                        image3_url = it.toString()
                        arrayList.add(it.toString())
                        Log.d("CityEntry",image3_url)

//                    saveUserToFirebaseDatabase(it.toString())

                    }
                    ref4.downloadUrl.addOnSuccessListener {

                        image4_url = it.toString()
                        arrayList.add(it.toString())
                        Log.d("CityEntry",image4_url)
//                    saveUserToFirebaseDatabase(it.toString())

                    }

                    for(i in arrayList){
                        Log.d("CityEntry","${i}")
                    }

//                    saveUserToFirebaseDatabase(city_image_url.toString(),image1_url.toString(),image2_url.toString(),image3_url.toString(),image4_url.toString())
//                    val ref = FirebaseDatabase.getInstance().getReference("").push()

                }



        }



    private fun saveUserToFirebaseDatabase(){

        val uid = UUID.randomUUID().toString()
        val ref = FirebaseDatabase.getInstance().getReference("/cityInformation/$uid").push()

        val cities = Cities(uid, editText_cityname.text.toString(),city_image_url.toString(),image1_url.toString(),image2_url.toString(),image3_url.toString(),image4_url.toString())
        ref.setValue(cities)
            .addOnSuccessListener {
                val intent = Intent(this,MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }

    }

    }

