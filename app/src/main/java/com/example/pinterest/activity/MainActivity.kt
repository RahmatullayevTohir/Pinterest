package com.example.pinterest.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pinterest.R
import com.example.pinterest.fragment.MessageFragment
import com.example.pinterest.fragment.HomeFragment
import com.example.pinterest.fragment.ProfileFragment
import com.example.pinterest.fragment.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item -> // By using switch we can easily get
            // the selected fragment
            // by using there id.
            var selectedFragment: Fragment = HomeFragment()
            when (item.itemId) {
                R.id.nav_home -> selectedFragment = HomeFragment()
                R.id.nav_search -> selectedFragment = SearchFragment()
                R.id.nav_comment -> selectedFragment = MessageFragment()
                R.id.nav_profile -> selectedFragment = ProfileFragment()
            }
            // It will help to replace the
            // one fragment to other.
            replaceFragment(selectedFragment)
            true
        }

    lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        bottomNavigationView = findViewById(R.id.bnv_main)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)
        bottomNavigationView.selectedItemId = R.id.nav_home
//        replaceFragment(HomeFragment())


    }

    fun replaceFragment(fragment: Fragment){
//        supportFragmentManager.beginTransaction().replace(R.id.view_container, fragment).commit()
        val backStateName = fragment.javaClass.name
        val manager = supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val ft = manager.beginTransaction()
            ft.replace(R.id.view_container, fragment)
            ft.addToBackStack(backStateName)
            ft.commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    fun hideBottomNavigation() {
        bottomNavigationView.visibility = View.GONE
    }

}