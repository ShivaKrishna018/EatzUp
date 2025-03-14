package com.kronos.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kronos.adapter.NotificationAdapter
import com.kronos.eatzup.R
import com.kronos.eatzup.databinding.FragmentNotificationBinding


class NotificationFragment : BottomSheetDialogFragment() {

    lateinit var notificationBinding : FragmentNotificationBinding
    lateinit var Adapter : NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       notificationBinding = FragmentNotificationBinding.inflate(layoutInflater, container, false)
        val notificationInfo = listOf("Your order has been cancelled successfully", "Order has been taken by the driver", "Congrats your order placed")
        val imagesList = listOf(R.drawable.sad,R.drawable.drive,R.drawable.tick)

        Adapter = NotificationAdapter(notificationInfo, imagesList)
        notificationBinding.notificationRecycler.layoutManager = LinearLayoutManager(requireContext())
        notificationBinding.notificationRecycler.adapter = Adapter

        return notificationBinding.root


    }


}