package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class BookingFragment : Fragment(R.layout.fragment_booking) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtParkingName = view.findViewById<TextView>(R.id.txtParkingName)
        val txtAvailability = view.findViewById<TextView>(R.id.txtAvailability)

        val isDark = ThemeUtils.isDarkTheme(requireContext())

        if (isDark) {
            // Change Background to Dark Grey
            view.setBackgroundColor(android.graphics.Color.parseColor("#121212"))

            txtParkingName.setTextColor(android.graphics.Color.WHITE)
            txtAvailability.setTextColor(android.graphics.Color.LTGRAY)
        } else {
            // Light Mode Defaults
            view.setBackgroundColor(android.graphics.Color.WHITE)
            txtParkingName.setTextColor(android.graphics.Color.BLACK)
        }

        // Retrieve data from Arguments (instead of Intent)
        val name = arguments?.getString("parking_name") ?: "Unknown"
        val price = arguments?.getString("parking_price") ?: "-"
        val imageRes = arguments?.getInt("parking_image", R.drawable.parking1) ?: R.drawable.parking1

        val btnBookNow = view.findViewById<Button>(R.id.btnBookNow)

        // Set the Click Listener
        btnBookNow.setOnClickListener {
            // parking object
            val selectedParking = Parking(name, price, imageRes)

            // Save it to our new CartManager
            CartManager.addItem(selectedParking)

            // Show success message
            Toast.makeText(requireContext(), "Added to Cart!", Toast.LENGTH_SHORT).show()

            parentFragmentManager.popBackStack()
        }

        // Find views using the 'view' parameter
        val imgParking = view.findViewById<ImageView>(R.id.imgParking)
        val txtPrice = view.findViewById<TextView>(R.id.txtPrice)

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