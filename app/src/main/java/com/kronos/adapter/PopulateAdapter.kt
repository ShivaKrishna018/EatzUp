package com.kronos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kronos.eatzup.databinding.CardFoodBinding

class PopulateAdapter(private val foodList: List<String>, private  val images: List<Int>): RecyclerView.Adapter<PopulateAdapter.PopulateViewHolder>() {
    class PopulateViewHolder(private val adapterBinding: CardFoodBinding): RecyclerView.ViewHolder(adapterBinding.root) {
        val imagesList = adapterBinding.foodImageList
        fun bind(foodItems: String, imageList: Int) {

            adapterBinding.FoodNameList.text = foodItems
            imagesList.setImageResource(imageList)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopulateViewHolder {
        return PopulateViewHolder(CardFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PopulateViewHolder, position: Int) {

        val foodItems = foodList[position]
        val imageList = images[position]
        holder.bind(foodItems, imageList)

    }

    override fun getItemCount(): Int {
       return foodList.size
    }
}