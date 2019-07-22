package com.example.trip_planner_home_page

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_register.*
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trip_planner_home_page.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.parcel.Parcelize
import java.util.*


private val TAG = "Dikkat aagyi"

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        button_register_login.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }


        auth = FirebaseAuth.getInstance()


        button_signup.setOnClickListener {

            checkUser()

        }

        button_photo_select_register.setOnClickListener {
            Log.d("MainActivity","try to show")

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,0)
        }
    }

    var selectedPhotoUri: Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==0 && resultCode == Activity.RESULT_OK  && data!=null){
            //proceed and check the selected image

            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,selectedPhotoUri)

            selectphoto_imageview_register.setImageBitmap(bitmap)

            button_photo_select_register.alpha = 0f

//            val bitmapDrawable = BitmapDrawable(bitmap)
//            button_photo_select_register.setBackgroundDrawable(bitmapDrawable)

        }


    }

    private fun checkUser() {
        if (editText_username.toString().isEmpty()) {
            editText_username.error = "Please enter username"
            editText_username.requestFocus()
            return
        }
        if (editText_email.toString().isEmpty()) {
            editText_email.error = "Please enter email"
            editText_email.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(editText_email.text.toString()).matches()) {
            editText_email.error = "Please enter valid email"
            editText_email.requestFocus()
            return
        }
        if (editText_password.toString().isEmpty()) {
            editText_password.error = "Please enter password"
            editText_password.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(editText_email.text.toString(), editText_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    uploadImagetoFirebaseStorage()
                    startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }

    }
    private fun uploadImagetoFirebaseStorage(){
        if(selectedPhotoUri == null) return

        val filename: String = UUID.randomUUID().toString()

        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")
        ref.putFile(selectedPhotoUri!!)

        .addOnSuccessListener {

            ref.downloadUrl.addOnSuccessListener {
                it.toString()
                saveUserToFirebaseDatabase(it.toString())
            }

        }
    }
    private fun saveUserToFirebaseDatabase(image_url: String){

        val uid = FirebaseAuth.getInstance().uid ?:""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid, editText_username.text.toString(),editText_email.text.toString(),image_url)
        ref.setValue(user)

    }
}
