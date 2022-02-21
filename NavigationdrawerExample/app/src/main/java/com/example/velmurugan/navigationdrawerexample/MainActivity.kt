package com.example.velmurugan.navigationdrawerexample

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.velmurugan.navigationdrawerexample.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import com.example.velmurugan.navigationdrawerexample.ImportFragment
import androidx.core.view.GravityCompat
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import com.example.velmurugan.navigationdrawerexample.GalleryFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.setCheckedItem(R.id.nav_camera)
        val fragmentManager = supportFragmentManager
        val fragment = ImportFragment()
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()

        val fragmentContainer = findViewById<ViewGroup>(R.id.external_box)
        fragmentContainer.setOnApplyWindowInsetsListener { view, insets ->
            var consumed = false

            (view as ViewGroup).forEach { child ->
                // Dispatch the insets to the child
                val childResult = child.dispatchApplyWindowInsets(insets)
                // If the child consumed the insets, record it
                if (childResult.isConsumed) {
                    consumed = true
                }
            }

            // If any of the children consumed the insets, return
            // an appropriate value
            if (consumed) insets.consumeSystemWindowInsets() else insets
        }
    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
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
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        var fragment: Fragment? = null
        val fragmentManager = supportFragmentManager
        if (id == R.id.nav_camera) {
            fragment = ImportFragment()
        } else if (id == R.id.nav_gallery) {
            fragment = GalleryFragment()
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment!!).commit()
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}