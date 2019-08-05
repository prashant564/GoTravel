package com.example.trip_planner_home_page

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat

import android.view.MenuItem

import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.trip_planner_home_page.models.Cities
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.current_city_row.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Select current city"



      fetchCities()


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

    companion object{
        val USER_KEY = "USER_KEY"
    }

    private fun fetchCities(){

        val ref = FirebaseDatabase.getInstance().getReference("/cityInformation")
        ref.addListenerForSingleValueEvent(object: ValueEventListener{

            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()
                p0.children.forEach {
                    Log.d("MainActivity", it.toString())
                    val cities = it.getValue(Cities::class.java)
                    if(cities!=null){
                        adapter.add(CitiesItem(cities))
                    }

                }

                adapter.setOnItemClickListener {item, view ->

                    val citiesItem = item as CitiesItem
                    val intent = Intent(view.context,DestinationCity::class.java)
                    intent.putExtra("USER_KEY",citiesItem.cities)

                    startActivity(intent)
                }
                current_city_recyclerView.adapter = adapter
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
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

    class CitiesItem(val cities: Cities) : Item<ViewHolder>(){

        override fun bind(viewHolder: ViewHolder, position: Int) {



            viewHolder.itemView.textView_current_city.text = cities.city_name
//
//        viewHolder.itemView.textView_current_city.text = cities.city_name
//        Log.d("MainActivity",cities.city_name)
        }

        override fun getLayout(): Int {
            return R.layout.current_city_row
        }
    }
}

