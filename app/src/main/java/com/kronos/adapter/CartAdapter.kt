package com.kronos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kronos.eatzup.databinding.CartAddOnsBinding

class CartAdapter(private val cartItem: MutableList<String>,private val cartPrice: MutableList<String>,private val cartImage: MutableList<Int> )
    :RecyclerView.Adapter<CartAdapter.CartViewHolder>(){


        private val itemQuantities = IntArray(cartItem.size){1}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
       return CartViewHolder(CartAddOnsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)

    }

    override fun getItemCount(): Int {
        return cartItem.size
    }

    inner class CartViewHolder(private val cartBinding:CartAddOnsBinding): RecyclerView.ViewHolder(cartBinding.root) {

        fun bind(position: Int) {
            cartBinding.apply {
                val quantity = itemQuantities[position]
                foodName.text = cartItem[position]
                amount.text = cartPrice[position]
                imagesList.setImageResource(cartImage[position])
                AddItem.text = quantity.toString()
                plusButton.setOnClickListener {
                    increaseQuantity(position)
                }
                minusButton.setOnClickListener {
                    decreaseQuantity(position)

                }
                trashButton.setOnClickListener {
                    val itemPosition = adapterPosition
                    if (itemPosition != RecyclerView.NO_POSITION) {
                        deleteAll(itemPosition)
                    }

                }

            }

        }

        private fun increaseQuantity(position: Int) {
            if (itemQuantities[position] < 10) {
                itemQuantities[position]++
                cartBinding.AddItem.text = itemQuantities[position].toString()
            }
        }

        private fun decreaseQuantity(position: Int) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--
                cartBinding.AddItem.text = itemQuantities[position].toString()
            }
        }

        private fun deleteAll(position: Int){
            cartItem.removeAt(position)
            cartPrice.removeAt(position)
            cartImage.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartItem.size)
        }

    }





}