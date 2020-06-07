package com.example.mydaiilynews

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.GenericTransitionOptions.with
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.github.curioustechizen.ago.RelativeTimeTextView
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.text.ParseException
import java.util.*

class ListNewsAdapter(val articleList:MutableList<Article>,val context: Context): PagerAdapter() {
    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return articleList.size
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.card,container,false)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val textViewTitle = view.findViewById<TextView>(R.id.Title)
        val textDescription = view.findViewById<TextView>(R.id.Description)
        val newsImage = view.findViewById<ImageView>(R.id.ImageView)
        val newsAuthor = view.findViewById<TextView>(R.id.Author)
        val timeInHoursAgo = view.findViewById<RelativeTimeTextView>(R.id.time1)
        val date = view.findViewById<TextView>(R.id.publishedAt)
        val expandButton = view.findViewById<ImageButton>(R.id.ExpandButton1)
        val source = view.findViewById<TextView>(R.id.source)
        if (articleList[position].urlToImage != null && articleList[position].urlToImage!!.isNotEmpty()) {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(Utils.getrandomDrawbleColor())
            requestOptions.error(Utils.getrandomDrawbleColor())
            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
            requestOptions.centerCrop()
            with(context)
                .load(articleList[position].urlToImage)
                .apply(requestOptions)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar!!.visibility = View.GONE
                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar!!.visibility = View.GONE
                        return false
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(newsImage!!)
        }
        if (articleList[position].title!!.length > 65) {
            textViewTitle!!.text = articleList[position].title!!.substring(0, 65) + "..."
        } else {
            textViewTitle!!.text = articleList[position].title!!
        }
        if (articleList[position].publishedAt != null) {
            var date: Date? = null
            try {
                date = ISO8601Parse.parse(articleList[position].publishedAt!!)
            } catch (ex: ParseException) {
                ex.printStackTrace()
            }
            if (date != null) {
                timeInHoursAgo!!.setReferenceTime(date.time)
            }
        }
        textDescription!!.text = articleList[position].description + "Click on the article to know more about the same,and get in detail information related to the article and gain more insights on the article..."
        newsAuthor!!.text = articleList[position].author
        source!!.text = articleList[position].author
        date!!.text = ISO8601Parse.DateFormat(articleList[position].publishedAt!!)
        //date.text = ISO8601Parse.toString(articleList[position].publishedAt!!)

        //holder.publishedDate.text = articleList[position].publishedAt?.let { ISO8601Parse.DateFormat(it) }
        // holder.source_of_article.text = articleList[position].
        expandButton!!.setOnClickListener {
            val intent = Intent(context, FullNewsActivity::class.java)
            intent.putExtra("webURL", articleList[position].url)
            context.startActivity(intent)
        }
        container.addView(view)
        return view
    }
}
//    RecyclerView.Adapter<ListNewsViewHolder>() {
//    //var website1:Source? = null
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNewsViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val itemView = inflater.inflate(R.layout.card,parent,false)
//        return ListNewsViewHolder(itemView)
//    }
//
//    override fun getItemCount(): Int {
//        return articleList.size
//    }
//
//    @SuppressLint("SetTextI18n", "CheckResult")
//    override fun onBindViewHolder(holder: ListNewsViewHolder, position: Int) {
//        val requestOptions = RequestOptions()
//        requestOptions.placeholder(Utils.getrandomDrawbleColor())
//        requestOptions.error(Utils.getrandomDrawbleColor())
//        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
//        requestOptions.centerCrop()
//        with(context)
//            .load(articleList[position].urlToImage)
//            .apply(requestOptions)
//            .listener(object : RequestListener<Drawable> {
//                override fun onResourceReady(
//                    resource: Drawable,
//                    model: Any,
//                    target: Target<Drawable>,
//                    dataSource: DataSource,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    holder.progressBar.visibility = View.GONE
//                    return false
//                }
//
//                override fun onLoadFailed(
//                    e: GlideException?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    holder.progressBar.visibility = View.GONE
//                    return false
//                }
//            })
//            .transition(DrawableTransitionOptions.withCrossFade())
//            .into(holder.article_image)
////        requestOptions.centerCrop()
////        Picasso.get()
////            .load(articleList[position].urlToImage)
////            .fit()
////            .into(holder.article_image, object: com.squareup.picasso.Callback {
////                override fun onSuccess() {
////                    holder.progressBar.visibility = View.GONE
////                }
////
////                override fun onError(e: Exception?) {
////                    holder.progressBar.visibility = View.VISIBLE
////                }
////
////            })
//        if(articleList[position].title!!.length > 65){
//            holder.article_title.text = articleList[position].title!!.substring(0,65)+"..."
//        }
//        else{
//            holder.article_title.text = articleList[position].title!!
//        }
//        if(articleList[position].publishedAt != null){
//            var date: Date? = null
//            try{
//                date = ISO8601Parse.parse(articleList[position].publishedAt!!)
//            }catch(ex: ParseException){
//                ex.printStackTrace()
//            }
//            if (date != null) {
//                holder.time_in_hours_ago.setReferenceTime(date.time)
//            }
//        }
//        holder.description.text = articleList[position].description
//        holder.article_author.text = articleList[position].author
//        holder.publishedDate.text = ISO8601Parse.DateFormat(articleList[position].publishedAt!!)
//        //holder.publishedDate.text = articleList[position].publishedAt?.let { ISO8601Parse.DateFormat(it) }
//      // holder.source_of_article.text = articleList[position].
//           holder.elaborateArticle.setOnClickListener{
//            val intent = Intent(context,FullNewsActivity::class.java)
//            intent.putExtra("webURL",articleList[position].url)
//            context.startActivity(intent)
//
//
//
//
//        }
//
//    }
//
//
//}