package com.example.mydaiilynews

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.GenericTransitionOptions.with
import com.bumptech.glide.Glide.with
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.text.ParseException
import java.util.*

class ListNewsAdapter(val articleList:MutableList<Article>,val context: Context):RecyclerView.Adapter<ListNewsViewHolder>() {
    var website1:Source? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.card,parent,false)
        return ListNewsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListNewsViewHolder, position: Int) {
        Picasso.get()
            .load(articleList[position].urlToImage)
            .fit()
            .into(holder.article_image, object: com.squareup.picasso.Callback {
                override fun onSuccess() {
                    holder.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    holder.progressBar.visibility = View.VISIBLE
                }

            })
        if(articleList[position].title!!.length > 65){
            holder.article_title.text = articleList[position].title!!.substring(0,65)+"..."
        }
        else{
            holder.article_title.text = articleList[position].title!!
        }
        if(articleList[position].publishedAt != null){
            var date: Date? = null
            try{
                date = ISO8601Parse.parse(articleList[position].publishedAt!!)
            }catch(ex: ParseException){
                ex.printStackTrace()
            }
            if (date != null) {
                holder.time_in_hours_ago.setReferenceTime(date.time)
            }
        }
        holder.description.text = articleList[position].description
        holder.article_author.text = articleList[position].author
        holder.publishedDate.text = ISO8601Parse.DateFormat(articleList[position].publishedAt!!)
        //holder.publishedDate.text = articleList[position].publishedAt?.let { ISO8601Parse.DateFormat(it) }
      // holder.source_of_article.text = articleList[position].
           holder.elaborateArticle.setOnClickListener{
            val intent = Intent(context,FullNewsActivity::class.java)
            intent.putExtra("webURL",articleList[position].url)
            context.startActivity(intent)




        }

    }


}