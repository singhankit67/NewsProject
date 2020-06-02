package com.example.mydaiilynews
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dmax.dialog.SpotsDialog
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_list_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListNews : AppCompatActivity() {
    var source = ""
    lateinit var dialog:AlertDialog
    lateinit var mService:NewsService
    var webHotUrl:String? = ""
    var recyclerView1: RecyclerView? = null
    //var swipeToRefresh1:SwipeRefreshLayout? = null
    lateinit var adapter:ListNewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_news)
        mService = Common.newsService
        dialog = SpotsDialog(this)
        recyclerView1 = findViewById(R.id.recyclerView1)
        recyclerView1!!.setHasFixedSize(true)
        recyclerView1!!.layoutManager = LinearLayoutManager(this)
        recyclerView1!!.itemAnimator = DefaultItemAnimator()
        recyclerView1!!.isNestedScrollingEnabled =true
        //recyclerView1!!.adapter = adapter
        swipeToRefresh1.setOnRefreshListener { loadNews(source,true) }
        if (intent != null) {
            source = intent.getStringExtra("source")
            if(!source.isEmpty()) {
                loadNews(source, false)

            }

        }
    }

    //lateinit var layoutManager:LinearLayoutManager
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.activity_list_news, container, false)
//        recyclerView1 = view?.findViewById(R.id.recyclerView1)
//        swipeToRefresh1 = view?.findViewById(R.id.swipeToRefresh1)
//        name = this.arguments?.getString("source_key")
//        //name.text
//        return view
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        myActivity = context as Activity
//    }
//
//    override fun onAttach(activity: Activity) {
//        super.onAttach(activity)
//        myActivity = activity
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        mService = Common.newsService
//        dialog = SpotsDialog(context)
//        //if(inte)
//        swipeToRefresh1?.setOnRefreshListener { loadNews(source, true) }
//        recyclerView1?.setHasFixedSize(true)
//        recyclerView1?.layoutManager = LinearLayoutManager(context)
//        if (name != null) {
//            source = name as String
//            if (!source.isEmpty())
//                loadNews(source, false)
//        }
//
////            }catch (e:Exception){
////                e.printStackTrace()
////            }
//
//        }
//
//
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        mService = Common.newsService
//        dialog = SpotsDialog(context)
//        //if(inte)
//        swipeToRefresh1?.setOnRefreshListener { loadNews(source, true) }
//        recyclerView1?.setHasFixedSize(true)
//        recyclerView1?.layoutManager = LinearLayoutManager(context)
//        if (name != null) {
//            source = name as String
//            if (!source.isEmpty())
//                loadNews(source, false)
//        }
//    }
    fun loadNews(source:String?,isRefreshed:Boolean){
        if(isRefreshed){
            dialog.show()
            mService.getNewsFromSource(Common.getNewsApi(source!!)).enqueue(object :Callback<News>{
                override fun onFailure(call: Call<News>, t: Throwable) {
                    Toast.makeText(this@ListNews,"Failed1",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<News>, response: Response<News>) {
                    dialog.dismiss()
                    //webHotUrl = response.body()?.articles?.get(0)?.url
                    val removeFirstItem = response.body()!!.articles
                    //removeFirstItem?.removeAt(0)
                    adapter = ListNewsAdapter(removeFirstItem!!,this@ListNews)
                    recyclerView1!!.adapter = adapter
                    adapter.notifyDataSetChanged()
                    swipeToRefresh1.isRefreshing = false

                }

            })
        }
        else{
            swipeToRefresh1.isRefreshing = true
            //dialog.show()
            mService.getNewsFromSource(Common.getNewsApi(source!!)).enqueue(object :Callback<News>{
                override fun onFailure(call: Call<News>, t: Throwable) {
                    Toast.makeText(this@ListNews,"Failed2", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<News>, response: Response<News>) {
                    swipeToRefresh1.isRefreshing =false
                    //webHotUrl = response.body()?.articles!![0].url
                    val removeFirstItem = response.body()!!.articles
                    //removeFirstItem!!.removeAt(0)
                    adapter = ListNewsAdapter(removeFirstItem!!,this@ListNews)
                    recyclerView1!!.adapter = adapter
                    adapter.notifyDataSetChanged()
                    swipeToRefresh1.isRefreshing = false
                }

            })
        }

    }
}
