package com.example.myapplication

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity

class BookingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val backBtn = findViewById<ImageButton>(R.id.backButton)

        backBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Get passed data
        val name = intent.getStringExtra("parking_name") ?: "Unknown"
        val price = intent.getStringExtra("parking_price") ?: "-"
        val imageRes = intent.getIntExtra("parking_image", R.drawable.parking1)
        // Find views
        val imgParking = findViewById<ImageView>(R.id.imgParking)
        val txtParkingName = findViewById<TextView>(R.id.txtParkingName)
        val txtPrice = findViewById<TextView>(R.id.txtPrice)
        // Set data
        txtParkingName.text = name
        txtPrice.text = price
        imgParking.setImageResource(imageRes)
    }
}
