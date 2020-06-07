package com.example.mydaiilynews

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydaiilynews.ui.main.RecentActivity
import kotlinx.android.synthetic.main.cardview.view.*
import java.util.*

class ListSourceAdapter(val website: WebSite, val context: Context): RecyclerView.Adapter<ListSourceViewHolder> (){
    //this defines that we want an array list where each element is int the form of model and it should be named as Model Adapter
//    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
//    {
//        fun bindItems(model:Model){
//            //here we have bind the id'S with the methods that we want to perform on that id's
//            itemView.CategoryName.text = model.name1
//
//            itemView.ExerciseImage.setImageResource(model.image)
//            itemView.LLforBackground.setBackgroundResource(model.background1)
//            itemView.CategoryName.setTextColor(model.headingColor)
//            itemView.content1.setTextColor(model.contentColor)
//        }
//    }
    var limit = 10
    lateinit var fm:FragmentManager
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSourceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.cardview1,parent,false)
        return ListSourceViewHolder(itemView)
        //val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview,parent,false) // tis means the changes should be made to this view
        //return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        if(website.sources!!.size > limit){
            return limit;
        }
        else {

            return website.sources!!.size // this makes as many copies of the views as we want
        }
    }
    override fun onBindViewHolder(holder: ListSourceViewHolder, position: Int) {
        holder.source_title.text = website.sources!![position].name
        //val source = website.sources!![position].name
        holder.cardViewb.setOnClickListener {
            val intent = Intent(context, ListNews::class.java)
            intent.putExtra("source", website.sources!![position].id)
            context.startActivity(intent)
        }
        holder.expandButton.setOnClickListener {
            val intent = Intent(context,ListNews::class.java)
            intent.putExtra("source",website.sources!![position].id)
            context.startActivity(intent)


//            (context as FragmentActivity).supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.view_pager,listNews)
//                .addToBackStack("ListNews")
//                .commit()



//        val intent = Intent(context,ListNews::class.java)
//            intent.putExtra("source", website.sources!![position].id)
//            context.startActivity(intent)
    }
        }
//    fun openListNews(source:String){
//        val bundle = Bundle()
//        bundle.putString("source_key", source)
//        //args.putParcelable("websitee",website)
//        //listNews.arguments = args
//        val fragment = ListNews()
//        fragment.arguments = bundle
//        (context as FragmentActivity).supportFragmentManager
//                .beginTransaction()
//               .replace(R.id.view_pager,fragment)
//                .addToBackStack("ListNews")
//                .commit()
//        var epicDialog = Dialog(context)//this means tha the dialog should be created here
//        epicDialog.setContentView(R.layout.activity_onexerciseclick)//the dialog made inside this layout can be used here
//        epicDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

//        holder.itemView.setOnClickListener{ //here we are assigning a variable to all the values that are to be changed dynamically and are going to bind it in the onClickListener
//            val model = arrayList[position]
//            val recyclerView = epicDialog.findViewById<RecyclerView>(R.id.recyclerView)
//            val ExerciseHeading = model.name1
//            val TimeHeading = model.time
//            recyclerView.setHasFixedSize(true)
//            recyclerView.layoutManager = LinearLayoutManager(context)
//            val youtubeVideos = Vector<YoutubeVideo>()
//            youtubeVideos.add(YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ykJmrZ5v0Oo\" frameborder=\"0\" allowfullscreen></iframe>"))
//            //"<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ykJmrZ5v0Oo\" frameborder=\"0\" allowfullscreen></iframe>"
//            val videoAdapter = VideoAdapter(youtubeVideos)
//            recyclerView.adapter = videoAdapter
//            epicDialog.exerciseHeading.text= ExerciseHeading
//            epicDialog.exerciseTime.text  = TimeHeading
//            epicDialog.show()
//            epicDialog.end.setOnClickListener { epicDialog.dismiss() }
        }
//        holder.itemView.setOnClickListener(View.OnClickListener{//what this does is from main activity it comes to random then goes to onclick intent activity if this is not given then from main activity it directly jumps to onclick activity
//        val model = arrayList[position]
//        val gTitle = model.name1
//        val gTime = model.time
//        val gTitleColor = model.headingColor
//        val gTimeColor = model.descriptionColor
//            setContentView(R.layout.activity_onexerciseclick)
//            var epicDialog: Dialog = Dialog(this.context)

        //val intent = Intent(context,Onexerciseclick::class.java) //this is the link that get activated by clicking on a particular object
        //intent.putExtra("iTitle" , gTitle)
        //intent.putExtra("iTime" , gTime)
        //intent.putExtra("iTitleColor" , gTitleColor)
        //intent.putExtra("iTimeColor" , gTimeColor)
        //the above two putExtra are put because we have to use these values in other activity
        //now we start the onclick activity
        //context.startActivity(intent)
        //})

//    override fun onRowMoved(fromPosition:Int, toPosition:Int) {
//        if (fromPosition < toPosition)
//        {
//            for (i in fromPosition until toPosition)
//            {
//                Collections.swap(arrayList, i, i + 1)
//            }
//        }
//        else
//        {
//            for (i in fromPosition downTo toPosition + 1)
//            {
//                Collections.swap(arrayList, i, i - 1)
//            }
//        }
//        notifyItemMoved(fromPosition, toPosition)
//    }
//    override fun onRowSelected(myViewHolder:ViewHolder) {
//        val card_view = myViewHolder.itemView.findViewById<LinearLayout>(R.id.LLforBackground1)
//        card_view.setBackgroundColor(Color.parseColor("#33BB86FC"))
//    }
//    override fun onRowClear(myViewHolder: ViewHolder) {
//        val card_view = myViewHolder.itemView.findViewById<LinearLayout>(R.id.LLforBackground1)
//        card_view.setBackgroundResource(R.drawable.rect3)
//    }
//    fun removeItem(position: Int) {
//        arrayList.removeAt(position)
//        notifyItemRemoved(position)
//    }
//    fun restoreItem(item: Model, position: Int) {
//        arrayList.add(position, item)
//        notifyItemInserted(position)
//    }
//    fun getData(): ArrayList<Model>? {
//        return arrayList
//    }
