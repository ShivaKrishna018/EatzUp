package com.kronos.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.kronos.adapter.CartAdapter
import com.kronos.adapter.PopulateAdapter
import com.kronos.eatzup.PayToCartActivity
import com.kronos.eatzup.R
import com.kronos.eatzup.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private val cartBinding: FragmentCartBinding by lazy {
        FragmentCartBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        cartBinding.proceed.setOnClickListener {
            val intent = Intent(requireContext(), PayToCartActivity::class.java)
            startActivity(intent)

        }
        return cartBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cartFoodItems = listOf("salad", "Beef Wellington", "Pasta", "Margherita Pizza", "Chicken Wing", "The Inferno Burger","Briyani", "Noodles", "Pasta", "Fried Rice", "Chicken Wing", "Fries")
        val cartPriceItems = listOf("110.89","120.99","80.89","130.99","160.99","70.59","110.89","120.99","80.89","130.99","160.99","70.59")
        val cartImages = listOf(R.drawable.pngegg,R.drawable.beef,R.drawable.pasta_png,R.drawable.pizza_full,R.drawable.chicken_png,R.drawable.burger_png,R.drawable.briyani,R.drawable.noodles,R.drawable.pasta_mid,R.drawable.fried_rice,R.drawable.chicken_png,R.drawable.french_fries)

        val recyclerView = cartBinding.cartRecyclerView

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = CartAdapter(ArrayList(cartFoodItems), ArrayList(cartPriceItems), ArrayList(cartImages))

        recyclerView.adapter = adapter
    }

}