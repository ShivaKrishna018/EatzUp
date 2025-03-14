package com.kronos.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.snapshot.Index
import com.kronos.MenuItems
import com.kronos.adapter.CategoryAdapter
import com.kronos.adapter.MenuAdapter
import com.kronos.adapter.PopulateAdapter
import com.kronos.eatzup.R
import com.kronos.eatzup.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var HomeBinding: FragmentHomeBinding

   // lateinit var categoryAdapter: CategoryAdapter
    private var showDialogSheet = ViewMenuFragment()
    lateinit var database : FirebaseDatabase
    lateinit var menuItemList: MutableList<MenuItems>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        HomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        HomeBinding.showAll.setOnClickListener {
            showDialogSheet.show(parentFragmentManager, "test")
        }
        HomeBinding.viewMenu.setOnClickListener {
            showDialogSheet.show(parentFragmentManager, "match")
        }

        retrieveAndPopulateData()
        return HomeBinding.root
    }

    private fun retrieveAndPopulateData() {

        database = FirebaseDatabase.getInstance()
        val foodRef: DatabaseReference = database.reference.child("menu")
        menuItemList = mutableListOf()

        foodRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(foodSnapshot in snapshot.children) {
                    val menuItem = foodSnapshot.getValue(MenuItems::class.java)
                    menuItem?.let{
                        menuItemList.add(it)
                    }
                    // display a random popular items
                    listRandomPopularItems()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun listRandomPopularItems() {
        //create as shuffled list of menu items

        val index = menuItemList.indices.toList().shuffled()
        val numItemShow = 5
        val subSetMenu: List<MenuItems> = index.take(numItemShow).map { menuItemList[it] }
        setPopularItemAdapter(subSetMenu)
    }

    private fun setPopularItemAdapter(subSetMenu: List<MenuItems>) {
        val adapter = MenuAdapter(subSetMenu, requireContext())

        HomeBinding.recyclerList.layoutManager = GridLayoutManager(requireContext(), 2)
        HomeBinding.recyclerList.adapter = adapter

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.burger, ScaleTypes.CENTER_INSIDE))
        imageList.add(SlideModel(R.drawable.pizza, ScaleTypes.CENTER_INSIDE))
        imageList.add(SlideModel(R.drawable.taco, ScaleTypes.CENTER_INSIDE))

        val imageSlider  = HomeBinding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_INSIDE)
        imageSlider.setItemClickListener(object : ItemClickListener{
            override fun doubleClick(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(position: Int) {
               val imagePosition = imageList[position]
                val imageMessage = "Selected $position"
                Toast.makeText(requireContext(), imageMessage, Toast.LENGTH_SHORT).show()
            }
        })

//        val foodList = listOf("Briyani", "Noodles", "Pasta", "Fried Rice", "Chicken Wing", "Fries")
//
//        val dishList = listOf("salad", "Beef Wellington", "Pasta", "Margherita Pizza", "Chicken Wing", "The Inferno Burger")
//
//        val foodDetail = listOf("Savory", "Sweet" , "Sour" , "Bitter" ,"spicy" , "Tangy")
//
//        val priceList = listOf("110.89","120.99","80.89","130.99","160.99","70.59")
//
//        val imagesList = listOf(R.drawable.briyani,R.drawable.noodles,R.drawable.pasta_mid,R.drawable.fried_rice,R.drawable.chicken_png,R.drawable.french_fries,)
//        val dishImageList = listOf(R.drawable.pngegg,R.drawable.beef,R.drawable.pasta_png,R.drawable.pizza_full,R.drawable.chicken_png,R.drawable.burger_png,)

//        val recyclerView = HomeBinding.recyclerList
//        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//
//        val adapter = PopulateAdapter(foodList, imagesList)
//        categoryAdapter = CategoryAdapter(dishList, foodDetail, priceList, dishImageList)
//
//        val categoryRecycler = HomeBinding.categoryList
//        categoryRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        categoryRecycler.adapter = categoryAdapter
//
//        recyclerView.adapter = adapter
    }
}
