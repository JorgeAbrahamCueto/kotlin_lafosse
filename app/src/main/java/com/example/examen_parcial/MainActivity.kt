package com.example.examen_parcial

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import android.content.Intent

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout =
            findViewById<androidx.drawerlayout.widget.DrawerLayout>(R.id.drawerLayout)
        drawerLayout.setDrawerLockMode(
            androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED
        )

        val navView =
            findViewById<com.google.android.material.navigation.NavigationView>(R.id.navView)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.app_name,
            R.string.app_name
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        replaceFragment(LoginFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.nav_home -> {
                replaceFragment(HomeFragment())
            }

            R.id.nav_nosotros -> {
                replaceFragment(NosotrosFragment())
            }

            R.id.nav_login -> {
                replaceFragment(LoginFragment())
            }

            R.id.nav_contacto -> {
                val ventana = Intent(this, ContactoActivity::class.java)
                startActivity(ventana)
            }

        }

        val drawerLayout =
            findViewById<androidx.drawerlayout.widget.DrawerLayout>(R.id.drawerLayout)

        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}