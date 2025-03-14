package com.kronos.eatzup

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultOwner
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kronos.eatzup.databinding.ActivityMainBinding
import com.kronos.fragment.NotificationFragment
import com.kronos.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.notificationBell.setOnClickListener {
            val notificationSheet = NotificationFragment()
            notificationSheet.show(supportFragmentManager,"text")
        }
        val navController = findNavController(R.id.fragmentContainerView)
        var bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigation.setupWithNavController(navController)

        binding.profileImage.setOnClickListener {
            navigation()
        }
    }

    fun navigation() {

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val profileFragment = ProfileFragment()
        fragmentTransaction.replace(R.id.fragmentContainerView, profileFragment)
        fragmentTransaction.commit()


    }
}