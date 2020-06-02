package com.example.mydaiilynews

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class Adapter(articles:List<Article>, context:Context): RecyclerView.Adapter<Adapter.MyViewHolder>() {
    private val articles:List<Article>
    private val context:Context
    private var onItemClickListener:OnItemClickListener? = null
    //val itemCount:Int
        //get() {
            //return articles.size
        //}
    init{
        this.articles = articles
        this.context = context
    }
    override fun onCreateViewHolder(@NonNull parent:ViewGroup, viewType:Int):MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card, parent, false)
        return MyViewHolder(view, onItemClickListener)
    }
    @SuppressLint("SetTextI18n", "CheckResult")
    override fun onBindViewHolder(@NonNull holders:MyViewHolder, position:Int) {
        val holder = holders
        val model = articles[position]
        val requestOptions = RequestOptions()
        requestOptions.placeholder(Utils.randomDrawbleColor)
        requestOptions.error(Utils.randomDrawbleColor)
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
        requestOptions.centerCrop()
        Glide.with(context)
            .load(model.urlToImage)
            .apply(requestOptions)
            .listener(object:RequestListener<Drawable> {
                override fun onResourceReady(resource:Drawable, model:Any, target:Target<Drawable>, dataSource:DataSource, isFirstResource:Boolean):Boolean {
                    holder.progressBar.visibility = View.GONE
                    return false
                }
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    holder.progressBar.visibility = View.GONE
                    return false
                }
            })
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.imageView)
        holder.title.text=model.title
        holder.desc.text=(model.description)
        //holder.source.text= model.source!!.name
        holder.time.text = " \u2022 " + model.publishedAt?.let { Utils.DateToTimeFormat(it) }
        holder.published_ad.setText(model.publishedAt?.let { Utils.DateFormat(it) })
        holder.author.setText(model.author)
    }
    fun setOnItemClickListener(onItemClickListener:OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
    interface OnItemClickListener {
        fun onItemClick(view:View, position:Int)
    }
    inner class MyViewHolder(itemView:View, onItemClickListener: OnItemClickListener?):RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal var title:TextView
        internal var desc:TextView
        internal var author:TextView
        internal var published_ad:TextView
        internal var source:TextView
        internal var time:TextView
        internal var imageView:ImageView
        internal var progressBar:ProgressBar
        internal lateinit var onItemClickListener:OnItemClickListener
        init{
            itemView.setOnClickListener(this)
            title = itemView.findViewById(R.id.title)
            desc = itemView.findViewById(R.id.Description)
            author = itemView.findViewById(R.id.Author)
            published_ad = itemView.findViewById(R.id.publishedAt)
            source = itemView.findViewById(R.id.source)
            time = itemView.findViewById(R.id.time)
            imageView = itemView.findViewById(R.id.ImageView)
            progressBar = itemView.findViewById(R.id.progressBar)
            if (onItemClickListener != null) {
                this.onItemClickListener = onItemClickListener
            }
        }
        override fun onClick(v:View) {
            onItemClickListener.onItemClick(v, getAdapterPosition())
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}