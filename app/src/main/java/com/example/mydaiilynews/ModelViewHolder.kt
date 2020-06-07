package com.example.mydaiilynews

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardview.view.*
import kotlinx.android.synthetic.main.cardview1.view.*

class ModelViewHolder (itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
    lateinit var itemClickListener:ItemClickListener
    var categorytitle = itemView.CategoryName
    var elaborateButton = itemView.ExpandButton
    var categoryImage = itemView.ExerciseImage
    var backgroundColor = itemView.LLforBackground
    fun setItemCLickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }
    override fun onClick(v: View?) {
        itemClickListener.onClick(v!!,adapterPosition)
    }
}