package com.example.myapplication

import android.os.Bundle
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
            Parking("Airport Parking", "30 EGP / hour", R.drawable.parking3) ,
            Parking("Carrefour", "25 EGP / hour", R.drawable.parking4)
        )


        binding.parkingView.layoutManager = LinearLayoutManager(this)
        binding.parkingView.adapter = ParkingAdapter(parkingList) { parking ->
            // This runs when a parking is clicked
            Toast.makeText(this, "Clicked: ${parking.name}", Toast.LENGTH_SHORT).show()
        }

    }
}

