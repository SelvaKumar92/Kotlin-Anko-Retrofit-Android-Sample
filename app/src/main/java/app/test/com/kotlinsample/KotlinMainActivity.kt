package app.test.com.kotlinsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import app.test.com.kotlinsample.AnkoDatabase.DbHelper
import app.test.com.kotlinsample.AnkoDesignActivity.UIActivity
import app.test.com.kotlinsample.Fragment.AnkoDesignListWithRetrofit
import app.test.com.kotlinsample.Fragment.Profile
import app.test.com.kotlinsample.FragmentManager.KotlinFragmentMananager

class KotlinMainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var myContext = this@KotlinMainActivity
    var myFragmentManager = KotlinFragmentMananager(this@KotlinMainActivity)
    val Context.database: DbHelper get() = DbHelper.getInstance(getApplicationContext())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadDrawer()


    }

    fun loadDrawer() {
        val aToolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(aToolbar)
        val aDrawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val aToggle = ActionBarDrawerToggle(this, aDrawer, aToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        aDrawer.setDrawerListener(aToggle)
        aToggle.syncState()
        val aNavigation = findViewById(R.id.nav_view) as NavigationView
        aNavigation.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
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
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        when (id) {
            R.id.nav_activity -> {
                val intent = Intent(this@KotlinMainActivity, UIActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_list_sample -> {
                myFragmentManager?.updateFragment(AnkoDesignListWithRetrofit())
            }
            R.id.nav_profile -> {
                myFragmentManager?.updateFragment(Profile())
            }
            else -> {
            }
        }

        val aDrawer = findViewById(R.id.drawer_layout) as DrawerLayout
        aDrawer.closeDrawer(GravityCompat.START)
        return true
    }
}
