package com.example.mydaiilynews

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.github.curioustechizen.ago.RelativeTimeTextView
import java.text.ParseException
import java.util.*

class Adapter(private val articles:MutableList<Article1>,val context:Context): PagerAdapter() {

    override fun getCount(): Int {
        return articles.size
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    @SuppressLint("SetTextI18n", "CheckResult")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view2 = LayoutInflater.from(context).inflate(R.layout.card,container,false)
        val title:TextView  = view2.findViewById(R.id.Title)
        val desc:TextView = view2.findViewById(R.id.Description)
        val author:TextView  = view2.findViewById(R.id.Author)
        val published_ad:TextView = view2.findViewById(R.id.publishedAt)
        val source:TextView = view2.findViewById(R.id.source)
        val time1:RelativeTimeTextView = view2.findViewById(R.id.time1)
        val imageView:ImageView = view2.findViewById(R.id.ImageView)
        val progressBar:ProgressBar = view2.findViewById(R.id.progressBar)
        val expandButton = view2.findViewById<ImageButton>(R.id.ExpandButton1)
        val model = articles[position]
        val requestOptions = RequestOptions()
        requestOptions.placeholder(Utils.getrandomDrawbleColor())
        requestOptions.error(Utils.getrandomDrawbleColor())
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
        requestOptions.centerCrop()
        Glide.with(context)
            .load(model.urlToImage)
            .apply(requestOptions)
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }
            })
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
        //holder.title.text=model.title
        //holder.desc.text=model.description
        source.text = model.source1!!.name
        if (model.title!!.length > 65) {
            title.text = model.title!!.substring(0, 65) + "..."
        } else {
            title.text = model.title!!
        }
        if (model.publishedAt != null) {
            var date: Date? = null
            try {
                date = ISO8601Parse.parse(model.publishedAt!!)
            } catch (ex: ParseException) {
                ex.printStackTrace()
            }
            if (date != null) {
                time1.setReferenceTime(date.time)
            }
        }
        desc.text = model.description + "Click on the article to know more about the same,and get in detail information related to the article and gain more insights on the article..."
        author.text = model.author
        published_ad.text = ISO8601Parse.DateFormat(model.publishedAt!!)
        expandButton!!.setOnClickListener {
            val intent = Intent(context, FullNewsActivity::class.java)
            intent.putExtra("webURL", model.url)
            context.startActivity(intent)
        }
        container.addView(view2)
        return view2
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }


}