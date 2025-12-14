package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ParkingAdapter(
    private val parkingList: List<Parking>,
    private val onItemClick: (Parking) -> Unit
) : RecyclerView.Adapter<ParkingAdapter.ParkingViewHolder>() {

    // ViewHolder class
    class ParkingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgParking: ImageView = itemView.findViewById(R.id.imgParking)
        val txtParkingName: TextView = itemView.findViewById(R.id.txtParkingName)
        val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingViewHolder {
        // Inflate the item layout
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_parking, parent, false)
        return ParkingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParkingViewHolder, position: Int) {
        // Get current parking item
        val currentParking = parkingList[position]

        // Set data to views
        holder.txtParkingName.text = currentParking.name
        holder.txtPrice.text = currentParking.price
        holder.imgParking.setImageResource(currentParking.imageRes)
        // Set click listener
        holder.itemView.setOnClickListener {
            onItemClick(currentParking)
        }
    }

    override fun getItemCount(): Int {
        return parkingList.size
    }
}