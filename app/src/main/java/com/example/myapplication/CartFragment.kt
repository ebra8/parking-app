package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartFragment : Fragment(R.layout.fragment_cart) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find Views
        val btnClear = view.findViewById<Button>(R.id.btnClear)
        val btnCheckout = view.findViewById<Button>(R.id.btnCheckout)
        val recyclerView = view.findViewById<RecyclerView>(R.id.cartRecyclerView)
        val txtEmpty = view.findViewById<TextView>(R.id.txtEmptyCart)
        val rootLayout = view.findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.cartRootLayout)

        // SETUP RECYCLERVIEW
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Helper function to refresh data
        fun updateList() {
            val items = CartManager.getItems()
            if (items.isEmpty()) {
                recyclerView.visibility = View.GONE
                txtEmpty.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                txtEmpty.visibility = View.GONE
                recyclerView.adapter = ParkingAdapter(items) {
                    // Optional: Click on item logic
                }
            }
        }

        // Initial load
        updateList()

        // SETUP CLEAR BUTTON
        btnClear.setOnClickListener {
            CartManager.clearCart()
            updateList() // Refresh the screen
            Toast.makeText(requireContext(), "Cart Cleared", Toast.LENGTH_SHORT).show()
        }

        // SETUP CHECKOUT BUTTON
        btnCheckout.setOnClickListener {
            val items = CartManager.getItems()
            if (items.isEmpty()) {
                Toast.makeText(requireContext(), "Cart is empty!", Toast.LENGTH_SHORT).show()
            } else {
                CartManager.clearCart()
                updateList()
                Toast.makeText(requireContext(), "Checkout Successful! Have a nice day.", Toast.LENGTH_LONG).show()
            }
        }

        // DARK MODE LOGIC
        val isDark = ThemeUtils.isDarkTheme(requireContext())
        if (isDark) {
            rootLayout.setBackgroundColor(android.graphics.Color.parseColor("#121212"))
            txtEmpty.setTextColor(android.graphics.Color.LTGRAY)
        } else {
            rootLayout.setBackgroundColor(android.graphics.Color.WHITE)
        }
    }
}