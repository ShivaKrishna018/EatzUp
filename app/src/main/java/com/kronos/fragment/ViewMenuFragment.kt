package com.kronos.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kronos.MenuItems
import com.kronos.adapter.CartAdapter
import com.kronos.adapter.MenuAdapter
import com.kronos.eatzup.R
import com.kronos.eatzup.databinding.FragmentViewMenuBinding

class ViewMenuFragment : BottomSheetDialogFragment() {


    private lateinit var database: FirebaseDatabase
    private lateinit var menuItem: MutableList<MenuItems>


    private val menuBinding: FragmentViewMenuBinding by lazy {
        FragmentViewMenuBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        menuBinding.backStack.setOnClickListener {
            dismiss()
        }
//        val cartFoodItems = listOf("Briyani", "Noodles", "Pasta", "Fried Rice", "Chicken Wing", "Fries","salad", "Beef Wellington", "Pasta", "Margherita Pizza", "Chicken Wing", "The Inferno Burger")
//        val cartPriceItems = listOf("$11.99","$12.99","$8.99","$12.99","$13.99","$7.99","110.89","120.99","80.89","130.99","160.99","70.59")
//        val cartImages = listOf(R.drawable.briyani,R.drawable.noodles,R.drawable.pasta_mid,R.drawable.fried_rice,R.drawable.chicken_png,R.drawable.french_fries,R.drawable.pngegg,R.drawable.beef,R.drawable.pasta_png,R.drawable.pizza_full,R.drawable.chicken_png,R.drawable.burger_png)

        retrieveMenuItem()

        return menuBinding.root
    }

    private fun retrieveMenuItem() {

        database = FirebaseDatabase.getInstance()
        val foodRef: DatabaseReference = database.reference.child("menu")
        menuItem = mutableListOf()

        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foodItemList in snapshot.children) {
                    val menuItemList: MenuItems? = foodItemList.getValue(MenuItems::class.java)

                    menuItemList?.let {
                        menuItem.add(it)
                    }

                    // once data receive, set to adapter
                    setAdapter()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun setAdapter() {
        val adapter = MenuAdapter(menuItem, requireContext())
        menuBinding.menuRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        menuBinding.menuRecycler.adapter = adapter
    }

    companion object {


    }
}