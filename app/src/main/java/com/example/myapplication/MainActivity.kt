package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity // Change to AppCompatActivity for Fragment support
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth


    private lateinit var globalHeader: View
    private lateinit var globalTitle: TextView
    private lateinit var globalBackBtn: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isDark = ThemeUtils.isDarkTheme(this)
        ThemeUtils.applyTheme(isDark)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()



        // Helper Function: Show Header
        fun showHeader(title: String) {
            globalHeader.visibility = View.VISIBLE
            globalTitle.text = title
        }

        // Helper Function: Hide Header (For Home Screen)
        fun hideHeader() {
            globalHeader.visibility = View.GONE
        }


        // Find the Header Views
        globalHeader = findViewById(R.id.globalHeader)
        globalTitle = findViewById(R.id.globalTitleText)
        globalBackBtn = findViewById(R.id.globalBackBtn)

        // Back Button Click
        globalBackBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Listen for Fragment Changes to Auto-Update the Header
        supportFragmentManager.addOnBackStackChangedListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

            // Logic: If there is a fragment open, show header. If null, we are Home.
            if (fragment != null) {
                // Check which fragment it is to set the title
                when (fragment) {
                    is CartFragment -> showHeader("My Cart")
                    is SettingsFragment -> showHeader("Settings")
                    is BookingFragment -> showHeader("Parking Details")
                    is ProfileFragment -> showHeader("Profile")
                    else -> showHeader("App")
                }
            } else {
                // We are on the Home screen
                hideHeader()
            }
        }


        val parkingList = listOf(
            Parking("Downtown Parking", "20 EGP / hour", R.drawable.parking1),
            Parking("Mall Parking", "15 EGP / hour", R.drawable.parking2),
            Parking("Airport Parking", "30 EGP / hour", R.drawable.parking3),
            Parking("Carrefour", "25 EGP / hour", R.drawable.parking4)
        )



        binding.parkingView.layoutManager = LinearLayoutManager(this)

        // Setup Adapter to load BookingFragment
        binding.parkingView.adapter = ParkingAdapter(parkingList) { parking ->
            val bookingFragment = BookingFragment.newInstance(
                parking.name,
                parking.price,
                parking.imageRes
            )
            loadFragment(bookingFragment)
        }

        val homeIcon = findViewById<ImageView>(R.id.homeIcon)
        homeIcon.setOnClickListener {
            // clear all fragments
            supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        val cartIcon = findViewById<ImageView>(R.id.cartIcon)
        cartIcon.setOnClickListener {
            loadFragment(CartFragment())
        }

        // Setup Profile Icon to load ProfileFragment
        val profileIcon = findViewById<ImageView>(R.id.profileIcon)
        profileIcon.setOnClickListener {
            loadFragment(ProfileFragment())
        }

        val settingsIcon = findViewById<ImageView>(R.id.settingsIcon)

        settingsIcon.setOnClickListener {
            loadFragment(SettingsFragment())
        }
    }

    // Helper function to swap fragments
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        transaction.replace(R.id.fragment_container, fragment)
        // Add to back stack so the "Back" button works naturally
        transaction.addToBackStack(null)
        transaction.commit()
    }
}