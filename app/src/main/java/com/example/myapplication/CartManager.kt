package com.example.myapplication

object CartManager {
    // This list holds all the parking spots added to the cart
    private val items = mutableListOf<Parking>()

    fun addItem(parking: Parking) {
        items.add(parking)
    }

    fun getItems(): List<Parking> {
        return items
    }

    fun clearCart() {
        items.clear()
    }
}