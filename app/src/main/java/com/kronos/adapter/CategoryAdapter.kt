package com.kronos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kronos.eatzup.databinding.CategoryListBinding

class CategoryAdapter(private val dishName: List<String>, private val dishDetails: List<String>, private val dishPrice: List<String>, private  val dishImage: List<Int>): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(val categoryBinding: CategoryListBinding): RecyclerView.ViewHolder(categoryBinding.root) {
        fun bind(
            dishNameInfo: String,
            dishPriceInfo: String,
            dishDetailInfo: String,
            dishImageInfo: Int
        ) {
            categoryBinding.dishName.text = dishNameInfo
            categoryBinding.amount.text = dishPriceInfo
            categoryBinding.dishDescription.text = dishDetailInfo
            categoryBinding.dishImage.setImageResource(dishImageInfo)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val dishNameInfo = dishName[position]
        val dishDetailInfo = dishDetails[position]
        val dishPriceInfo = dishPrice[position]
        val dishImageInfo = dishImage[position]
       holder.bind(dishNameInfo, dishPriceInfo, dishDetailInfo, dishImageInfo)
    }

    override fun getItemCount(): Int {
      return dishName.size
    }
}