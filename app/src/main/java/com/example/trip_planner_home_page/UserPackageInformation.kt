package com.example.trip_planner_home_page

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_user_package_information.*

class UserPackageInformation : AppCompatActivity() {

    val counterValue_adult = null
    val counterValue_child = null
    val counterValue_senior = null
    var counter_adult: Int = 0
    var counter_child: Int = 0
    var counter_senior: Int = 0

    var tripDate: String = null.toString()
    var flag: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_package_information)



        adultCounterOperation()
        childCounterOperation()
        seniorCounterOperation()

        calendarView_set_date.setOnDateChangeListener {view, year, month, dayOfMonth ->

            flag = 1
            tripDate =  "$dayOfMonth" + "-"  + (month+1) + "-" + year
            Toast.makeText(this@UserPackageInformation, tripDate, Toast.LENGTH_SHORT).show()

        }


        val extras: Bundle? = intent.extras
            val package_name: String? = extras!!.getString("package name")
            val package_price: String? = extras!!.getString("package price")

        supportActionBar?.title = package_name
//


            button_next.setOnClickListener {


                val intent = Intent(this@UserPackageInformation, PackageSummary::class.java)

                intent.putExtra("package name", package_name)


                var counterValue_adult = textView_counter_adult.text
                var counterValue_child = textView_child_counter.text
                var counterValue_senior = textView_counter_senior.text
                intent.putExtra("counterValue_adult", counterValue_adult)
                intent.putExtra("counterValue_child", counterValue_child)
                intent.putExtra("counterValue_senior", counterValue_senior)
                intent.putExtra("package price",package_price)
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

    private fun adultCounterOperation(){

        textView_counter_adult.setText("0")

        button_add_adult.setOnClickListener{
            counter_adult++
            textView_counter_adult.text = "$counter_adult"
        }

        button_subtract_adult.setOnClickListener {

            if (counter_adult > 0) {
                counter_adult--
                textView_counter_adult.text = "$counter_adult"
            }
        }
    }

    private fun childCounterOperation(){

        textView_child_counter.setText("0")

        button_child_add.setOnClickListener{
            counter_child++
            textView_child_counter.text = "$counter_child"
        }

        button_child_minus.setOnClickListener {

            if (counter_child > 0) {
                counter_child--
                textView_child_counter.text = "$counter_child"
            }
        }

    }
    private fun seniorCounterOperation(){

        textView_counter_senior.setText("0")

        button_add_senior.setOnClickListener{
            counter_senior++
            textView_counter_senior.text = "$counter_senior"
        }

        button_minus_senior.setOnClickListener {

            if (counter_senior > 0) {
                counter_senior--
                textView_counter_senior.text = "$counter_senior"
            }
        }

    }

        }














