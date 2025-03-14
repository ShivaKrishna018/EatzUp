package com.kronos.eatzup

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kronos.eatzup.databinding.ActivityPayToCartBinding

class PayToCartActivity : AppCompatActivity() {
    private val binding : ActivityPayToCartBinding by lazy {
        ActivityPayToCartBinding.inflate(layoutInflater)
    }
    private var foodName: String? = null
    private val foodImage: String? = null
    private var foodDescription: String? = null
    private var foodPrice: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        foodName = intent.getStringExtra("menuItemName")
        foodDescription = intent.getStringExtra("menuDescription" )
        foodPrice = intent.getStringExtra("menuPrice")

        with(binding){
            amount.text = foodPrice
            dishInfo.text = foodDescription
        }



    }
}