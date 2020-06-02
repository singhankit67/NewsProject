package com.example.mydaiilynews.ui.main

import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.mydaiilynews.R
import com.example.mydaiilynews.Source
import kotlinx.android.synthetic.main.cardview.view.*

class SourcesAdapter(context:Context, sourceList:List<Source>): RecyclerView.Adapter<SourcesAdapter.SourcesViewHolder>() {
    internal var context:Context
    internal var sourceList:List<Source>
//    val itemCount:Int
//        get() {
//            return sourceList.size
        //}
    init{
        this.context = context
        this.sourceList = sourceList
    }

    override fun onCreateViewHolder(@NonNull viewGroup:ViewGroup, i:Int):SourcesViewHolder {
        val inflater = LayoutInflater.from(viewGroup.getContext())
        val view = inflater.inflate(R.layout.cardview, viewGroup, false)
        return SourcesViewHolder(view)
    }
    override fun onBindViewHolder(@NonNull sourcesViewHolder:SourcesViewHolder, i:Int) {
        sourcesViewHolder.itemView.CategoryName.text = sourceList[i].name
    }
    inner class SourcesViewHolder(@NonNull itemView:View):RecyclerView.ViewHolder(itemView) {

        //@BindView(R.id.source_name) internal var textViewName:TextView
        init{
            //ButterKnife.bind(this, itemView)
            itemView.ExpandButton.setOnClickListener {
                val sourceItem = sourceList.get(getAdapterPosition())
                val intent = Intent(context, RecentActivity::class.java)
                intent.putExtra("sourceId", sourceItem.id)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return sourceList.size
    }
}