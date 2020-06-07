package com.example.mydaiilynews

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardview.view.*
import java.util.*

class ModelAdapter(private val arrayList: ArrayList<Model>, val context: Context):
    RecyclerView.Adapter<ModelViewHolder>() {
    //this defines that we want an array list where each element is int the form of model and it should be named as Model Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(
            R.layout.cardview,
            parent,
            false
        ) // tis means the changes should be made to this view
        return ModelViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return arrayList.size // this makes as many copies of the views as we want
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.categorytitle.text = arrayList[position].name1
        holder.backgroundColor.setBackgroundResource(arrayList[position].background1)
        holder.categoryImage.setImageResource(arrayList[position].image)
        holder.categorytitle.setTextColor(arrayList[position].headingColor)
        holder.elaborateButton.setOnClickListener {
            val intent = Intent(context, SportsActivity::class.java)
            intent.putExtra("url", arrayList[position].main)
            context.startActivity(intent)
        }
    }
}