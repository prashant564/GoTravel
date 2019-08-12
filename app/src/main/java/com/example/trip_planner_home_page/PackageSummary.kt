package com.example.trip_planner_home_page

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_package_summary.*

class PackageSummary : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package_summary)
        supportActionBar?.title = "Package Summary"

        val extras :Bundle? = intent.extras
        if(extras !=null){

            val adult_value: String? = intent.getStringExtra("counterValue_adult")
            val child_value: String? = intent.getStringExtra("counterValue_child")
            val senior_value: String? = intent.getStringExtra("counterValue_senior")
            val value: Int = adult_value!!.toInt() + child_value!!.toInt() + senior_value!!.toInt()


            val package_name: String? = intent.getStringExtra("package name")
            val package_price: String? = intent.getStringExtra("package price")
            val tripDate: String? = intent.getStringExtra("trip_date")

            val adult_price: Int = adult_value!!.toInt() * package_price!!.toInt()
            val child_price:Int = child_value!!.toInt()*(package_price!!.toInt() - (package_price!!.toInt()/2))
            val senior_price:Int = senior_value!!.toInt()*(package_price!!.toInt() - (package_price!!.toInt()/4))

            val price:Int = adult_price + child_price + senior_price
            val total_price = price + 0.18*price

            textView_adult_counter_value.text = adult_value
            textView_child_counter_value.text = child_value
            textView_senior_counter_value.text = senior_value
            textView_final_package_name.text = "$package_name"
            textView_package_adult_price.text = "\u20B9 ${adult_price}"
            textView_package_child_price.text = "\u20B9 ${child_price}"
            textView_package_senior_price.text = "\u20B9 ${senior_price}"
            textView_total_price_gst.text = "\u20B9 ${total_price}"
            textView_date.text = "$tripDate"
        }



        textView_pay_now.setOnClickListener { startActivity(Intent(this@PackageSummary, PaymentOptions::class.java)) }
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
}
