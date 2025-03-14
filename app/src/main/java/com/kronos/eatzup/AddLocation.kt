package com.kronos.eatzup

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kronos.eatzup.databinding.ActivityAddLocationBinding

class AddLocation : AppCompatActivity() {


    private val locationBinding : ActivityAddLocationBinding by lazy {
        ActivityAddLocationBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(locationBinding.root)


        val locationList : Array<String> = resources.getStringArray(R.array.locationList)

        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, locationList)

        val addCompleteTextList: AutoCompleteTextView = locationBinding.locationList

        addCompleteTextList.setAdapter(adapter)




    }
}