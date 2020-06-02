package com.example.mydaiilynews

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardview.view.*

class ListSourceViewHolder(itemView:View):RecyclerView.ViewHolder(itemView),View.OnClickListener {
    lateinit var itemClickListener:ItemClickListener
    var source_title = itemView.CategoryName
    var expandButton = itemView.ExpandButton
    fun setItemCLickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }
    override fun onClick(v: View?) {
        itemClickListener.onClick(v!!,adapterPosition)
    }
}