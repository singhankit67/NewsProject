package com.example.mydaiilynews

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card.view.*

class ListNewsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView),View.OnClickListener {
    private lateinit var itemClickListener:ItemClickListener
    var article_title = itemView.Title
    var article_author = itemView.Author
    var description = itemView.Description
    var time_in_hours_ago = itemView.time1
    var source_of_article = itemView.source
    var article_image = itemView.ImageView
    var publishedDate = itemView.publishedAt
    var progressBar = itemView.progressBar
    var elaborateArticle = itemView.ExpandButton1
    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }
    override fun onClick(v: View) {
        itemClickListener.onClick(v,adapterPosition)
    }
    //this is the view which on loading loads all the data from the website

}