package com.example.trip_planner_home_page

import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat

import android.view.MenuItem

import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {



    var values = arrayOf("DELHI","MUMBAI","MANALI")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



//        supportActionBar?.title = "Select current city"

        //Listview started

         val adapter = ArrayAdapter(this,
            R.layout.listview_item, values)


         val listView: ListView = findViewById(R.id.listView_current_city)

        listView.setAdapter(adapter)
        listView.setOnItemClickListener { parent, view, position, id ->

            val intent = Intent(this@MainActivity, DestinationCity::class.java)

            //getItemAtPosition
            var city_current:String = listView_current_city.getItemAtPosition(position).toString()
            intent.putExtra("city_current",city_current)

            startActivity(intent)

        }


        //listView Ended

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)


    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_your_profile -> {

                    startActivity(Intent(this@MainActivity, Your_Profile::class.java))

            }

            R.id.nav_contact_us -> {
                startActivity(Intent(this@MainActivity, Contact_Us::class.java))

            }
            R.id.nav_logout -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, RegisterActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }
            R.id.nav_t_and_c -> {
                startActivity(Intent(this@MainActivity, terms_and_conditions::class.java))

            }
            R.id.nav_rate_us -> {
                startActivity(Intent(this@MainActivity, RateUs::class.java))


            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
