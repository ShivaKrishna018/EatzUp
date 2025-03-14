package com.kronos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kronos.eatzup.databinding.NotificationResultBinding

class NotificationAdapter(private var infoText: List<String>, private var infoImage: List<Int>) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(NotificationResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {

        return infoText.size
    }
    inner class NotificationViewHolder(private val notificationBinding: NotificationResultBinding): RecyclerView.ViewHolder(notificationBinding.root) {
        fun bind(position: Int) {
            notificationBinding.apply {

                notificationText.text = infoText[position]
                notificationImage.setImageResource(infoImage[position])

            }
        }

    }
}