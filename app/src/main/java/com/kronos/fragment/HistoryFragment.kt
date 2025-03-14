package com.kronos.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.kronos.adapter.HistoryAdapter
import com.kronos.eatzup.R
import com.kronos.eatzup.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private val historyBinding : FragmentHistoryBinding by lazy {
        FragmentHistoryBinding.inflate(layoutInflater)
    }

    lateinit var historyAdapter: HistoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpRecyclerList()
        return historyBinding.root
    }

    private fun setUpRecyclerList() {
        // Inflate the layout for this fragment
        val foodList = arrayListOf("Briyani", "Noodles", "Pasta", "Fried Rice", "Chicken Wing", "Fries")
        val priceList = arrayListOf("$11.99","$12.99","$8.99","$12.99","$13.99","$7.99")
        val imagesList = arrayListOf(R.drawable.briyani,R.drawable.noodles,R.drawable.pasta_mid,R.drawable.fried_rice,R.drawable.chicken_png,R.drawable.french_fries,)
        historyAdapter = HistoryAdapter(foodList, priceList, imagesList)

        historyBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        historyBinding.recyclerView.adapter = historyAdapter
    }


}