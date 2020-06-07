package com.example.mydaiilynews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fun backGroundColor() {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
            window.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent)
            window.setBackgroundDrawableResource(R.drawable.background)
        }
        setContentView(R.layout.activity_main)
        backGroundColor()
        val arrayList = ArrayList<Model>()
        arrayList.add(Model("SPORTS",R.drawable.ic_directions_run_black_24dp,Color.parseColor("#004742"), Color.parseColor("#ebe8cf"),
            "ankit"))
        arrayList.add(Model("START-UPS",R.drawable.ic_trending_up_black_24dp,Color.parseColor("#1e665b"), Color.parseColor("#ebe8cf"), "ankit"))
        arrayList.add(Model("BUSINESS",R.drawable.ic_supervisor_account_black_24dp,Color.parseColor("#1e665b"), Color.parseColor("#ebe8cf"), "ankit"))
        arrayList.add(Model("ENTERTAINMENT",R.drawable.ic_videocam_black_24dp,Color.parseColor("#004742"), Color.parseColor("#ebe8cf"),
            "ankit"))
        arrayList.add(Model("POLITICS",R.drawable.ic_euro_symbol_black_24dp,Color.parseColor("#004742"), Color.parseColor("#ebe8cf"),
            "ankit"))
        arrayList.add(Model("FINANCE & ECONOMY",R.drawable.ic_euro_symbol_black_24dp,Color.parseColor("#1e665b"), Color.parseColor("#ebe8cf"),
            "ankit"))
        arrayList.add(Model("INTERNATIONAL",R.drawable.ic_public_black_24dp,Color.parseColor("#1e665b"), Color.parseColor("#ebe8cf"),
            "ankit"))
        val modelAdapter = ModelAdapter(arrayList,this) // this means that the this all things should be implemented here
        val gridLayoutManager = GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = gridLayoutManager // here the view is attached to recycler view that means this should be implemented inside the recycler view
        recyclerView.adapter = modelAdapter
    }
}
