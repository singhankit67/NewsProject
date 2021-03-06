package com.example.mydaiilynews

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardview.view.*
import kotlinx.android.synthetic.main.cardview1.view.*

class ListSourceViewHolder(itemView:View):RecyclerView.ViewHolder(itemView),View.OnClickListener {
    lateinit var itemClickListener:ItemClickListener
    var source_title = itemView.CategoryName1
    var expandButton = itemView.ExpandButton2
    var cardViewb = itemView.cardViewnib1
    fun setItemCLickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }
    override fun onClick(v: View?) {
        itemClickListener.onClick(v!!,adapterPosition)
    }
}