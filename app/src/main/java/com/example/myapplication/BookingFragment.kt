package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

// Inherit from Fragment instead of ComponentActivity
// pass your layout ID (R.layout.activity_booking) directly to the constructor
class BookingFragment : Fragment(R.layout.fragment_booking) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backBtn = view.findViewById<ImageButton>(R.id.backButton)

        backBtn.setOnClickListener {
            // "Go back" in fragment terms usually means popping the stack
            parentFragmentManager.popBackStack()
        }

        // Retrieve data from Arguments (instead of Intent)
        val name = arguments?.getString("parking_name") ?: "Unknown"
        val price = arguments?.getString("parking_price") ?: "-"
        val imageRes = arguments?.getInt("parking_image", R.drawable.parking1) ?: R.drawable.parking1

        // Find views using the 'view' parameter
        val imgParking = view.findViewById<ImageView>(R.id.imgParking)
        val txtParkingName = view.findViewById<TextView>(R.id.txtParkingName)
        val txtPrice = view.findViewById<TextView>(R.id.txtPrice)

        // Set data
        txtParkingName.text = name
        txtPrice.text = price
        imgParking.setImageResource(imageRes)
    }

    // Helper to create the fragment with arguments easily
    companion object {
        fun newInstance(name: String, price: String, imageRes: Int): BookingFragment {
            val fragment = BookingFragment()
            val args = Bundle()
            args.putString("parking_name", name)
            args.putString("parking_price", price)
            args.putInt("parking_image", imageRes)
            fragment.arguments = args
            return fragment
        }
    }
}