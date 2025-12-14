package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity // Change to AppCompatActivity for Fragment support
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isDark = ThemeUtils.isDarkTheme(this)
        ThemeUtils.applyTheme(isDark)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val parkingList = listOf(
            Parking("Downtown Parking", "20 EGP / hour", R.drawable.parking1),
            Parking("Mall Parking", "15 EGP / hour", R.drawable.parking2),
            Parking("Airport Parking", "30 EGP / hour", R.drawable.parking3),
            Parking("Carrefour", "25 EGP / hour", R.drawable.parking4)
        )



        binding.parkingView.layoutManager = LinearLayoutManager(this)

        // 1. Setup Adapter to load BookingFragment
        binding.parkingView.adapter = ParkingAdapter(parkingList) { parking ->
            val bookingFragment = BookingFragment.newInstance(
                parking.name,
                parking.price,
                parking.imageRes
            )
            loadFragment(bookingFragment)
        }

        // 2. Setup Profile Icon to load ProfileFragment
        val profileIcon = findViewById<ImageView>(R.id.profileIcon)
        profileIcon.setOnClickListener {
            loadFragment(ProfileFragment())
        }

        // Settings can remain an Activity for now, or you can convert it similarly!
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