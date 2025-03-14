package com.kronos.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kronos.MenuItems
import com.kronos.eatzup.PayToCartActivity
import com.kronos.eatzup.R
import com.kronos.eatzup.databinding.SearchInfoViewBinding

class MenuAdapter(private val menuList: List<MenuItems>,
    private val requireContext: Context): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

        var imageIndex = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.MenuViewHolder {
        return MenuViewHolder(SearchInfoViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MenuAdapter.MenuViewHolder, position: Int) {
       holder.bind(position)
        val imageReplace = holder.menuBinding.heartSymbol
        holder.menuBinding.heartSymbol.setOnClickListener {
            if (imageIndex==0) {
                imageReplace.setImageResource(R.drawable.love)
                imageIndex = 1
            }else {
                imageReplace.setImageResource(R.drawable.heart)
                imageIndex = 0
            }

        }
        getDataToInfo(position)
    }

    private fun getDataToInfo(position: Int) {
        val menuItem = menuList[position]
        // a intent to open details activity and pass data
        val intent = Intent(requireContext, PayToCartActivity::class.java).apply {
            putExtra("menuItemName", menuItem.foodName)
            putExtra("menuImage", menuItem.foodImageUri)
            putExtra("menuDescription", menuItem.foodDescription)
            putExtra("menuPrice", menuItem.foodPrice)
        }
        requireContext.startActivity(intent)
    }


    override fun getItemCount(): Int {
        return menuList.size
    }
    inner class MenuViewHolder( val menuBinding: SearchInfoViewBinding):RecyclerView.ViewHolder(menuBinding.root) {
        fun bind(position: Int) {

            // set data in to recyclerView name, price, image
            val menuItem = menuList[position]
            menuBinding.apply {
                FoodNameList.text = menuItem.foodName
                amount.text = menuItem.foodPrice
                val uri = Uri.parse(menuItem.foodImageUri)
                Glide.with(requireContext).load(uri).into(foodImageList)
            }

        }

    }

}