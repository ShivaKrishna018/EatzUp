package com.kronos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kronos.eatzup.databinding.RecentHistoryItemsBinding

class HistoryAdapter(private val orderItem: ArrayList<String>,
    private val orderPrice: ArrayList<String>,
    private val orderImage: ArrayList<Int>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(RecentHistoryItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(orderItem[position],orderPrice[position],orderImage[position],)
    }

    override fun getItemCount(): Int {
      return orderItem.size
    }

    inner class HistoryViewHolder(private val historyBinding: RecentHistoryItemsBinding): RecyclerView.ViewHolder(historyBinding.root) {
        fun bind(orderItem: String, orderPrice: String, orderImage: Int) {

            historyBinding.recentMenuNameList.text = orderItem
            historyBinding.recentMenuPriceList.text = orderPrice
            historyBinding.recentFoodImageList.setImageResource(orderImage)

        }
    }
}