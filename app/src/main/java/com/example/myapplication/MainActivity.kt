package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity

import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()


        // Display user's name if available
        val currentUser = auth.currentUser
        val userName = currentUser?.displayName ?: "User"

        val parkingList = listOf(
            Parking("Downtown Parking", "20 EGP / hour", R.drawable.parking1),
            Parking("Mall Parking", "15 EGP / hour", R.drawable.parking2),
            Parking("Airport Parking", "30 EGP / hour", R.drawable.parking3),
            Parking("Carrefour", "25 EGP / hour", R.drawable.parking4)
        )


        binding.parkingView.layoutManager = LinearLayoutManager(this)
        binding.parkingView.adapter = ParkingAdapter(parkingList) { parking ->
            val intent = Intent(this, BookingActivity::class.java)
            intent.putExtra("parking_name", parking.name)
            intent.putExtra("parking_price", parking.price)
            intent.putExtra("parking_image", parking.imageRes)
//            intent.putExtra("parking_availability", parking.availability)
            startActivity(intent)
        }

        val profileIcon = findViewById<ImageView>(R.id.profileIcon)
        profileIcon.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val settingsIcon = findViewById<ImageView>(R.id.settingsIcon)
        settingsIcon.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }



    }
}

