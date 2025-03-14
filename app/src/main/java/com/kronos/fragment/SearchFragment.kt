package com.kronos.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kronos.adapter.MenuAdapter
import com.kronos.eatzup.R
import com.kronos.eatzup.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private val searchBinding: FragmentSearchBinding by lazy {
        FragmentSearchBinding.inflate(layoutInflater)
    }
    private lateinit var adapter: MenuAdapter

    val searchMenuItems = listOf("salad", "Beef Wellington", "Pasta", "Margherita Pizza", "Chicken Wing", "The Inferno Burger","Briyani", "Noodles", "Pasta", "Fried Rice", "Chicken Wing", "Fries")
    val searchMenuPrice = listOf("110.89","120.99","80.89","130.99","160.99","70.59","110.89","120.99","80.89","130.99","160.99","70.59")
    val searchMenuImages = listOf(R.drawable.pngegg,R.drawable.beef,R.drawable.pasta_png,R.drawable.pizza_full,R.drawable.chicken_png,R.drawable.burger_png,R.drawable.briyani,R.drawable.noodles,R.drawable.pasta_mid,R.drawable.fried_rice,R.drawable.chicken_png,R.drawable.french_fries)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

   private val filterItem = mutableListOf<String>()
   private val filterPrice = mutableListOf<String>()
    private val filterImage = mutableListOf<Int>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        adapter = MenuAdapter(filterItem, filterPrice, filterImage)
        searchBinding.searchRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        searchBinding.searchRecycler.adapter = adapter

        showAllItem()
        setUpSearchView()
        return searchBinding.root
    }

    private fun showAllItem() {
        filterItem.clear()
        filterPrice.clear()
        filterImage.clear()

        filterItem.addAll(searchMenuItems)
        filterPrice.addAll(searchMenuPrice)
        filterImage.addAll(searchMenuImages)

        adapter.notifyDataSetChanged()

    }

    private fun setUpSearchView() {
        searchBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItem(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
               filterMenuItem(newText)
                return true
            }
        })
    }

    private fun filterMenuItem(query: String) {

        filterItem.clear()
        filterPrice.clear()
        filterImage.clear()

        searchMenuItems.forEachIndexed { index, foodItem ->
            if (foodItem.contains(query, ignoreCase = true)) {
                filterItem.add(foodItem)
                filterPrice.add(searchMenuPrice[index])
                filterImage.add(searchMenuImages[index])

            }
        }

        adapter.notifyDataSetChanged()

    }
}
