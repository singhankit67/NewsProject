package com.example.mydaiilynews.ui.main

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mydaiilynews.*
import com.google.gson.Gson
import dmax.dialog.SpotsDialog
import io.paperdb.Paper
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeActivity : Fragment(){
    var myActivity: Activity? = null
    var recyclerView:RecyclerView? = null
    var recyclerView3:RecyclerView? = null
    lateinit var mService:NewsService
    lateinit var adapter:ListSourceAdapter
    lateinit var dialog:AlertDialog
    var swipeTorefresh:SwipeRefreshLayout? = null
    var relativeLayout :RelativeLayout? = null
    var imageView:ImageView? = null
    var text1:TextView? = null
    var button1:Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view?.findViewById(R.id.recyclerView)
        recyclerView3 = view?.findViewById(R.id.recyclerView3)
        relativeLayout = view?.findViewById(R.id.errorLayout)
        imageView = view?.findViewById(R.id.errorImage)
        text1 = view?.findViewById(R.id.errorMessage)
        button1  = view?.findViewById(R.id.errorButton)
//        recyclerView?.setHasFixedSize(true)
//        val gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
//        recyclerView?.layoutManager = gridLayoutManager

        swipeTorefresh = view?.findViewById(R.id.swipeToRefresh)

        return view
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        myActivity = context as Activity
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        myActivity = activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        backGroundColor()
//        Paper.init(context) //Init cache Db
//        mService = Common.newsService //InitService
//        swipeTorefresh?.setOnRefreshListener { //what to do on the refresh option
//            loadWebsiteSource(true) }
//        recyclerView?.setHasFixedSize(true)
//        val gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
//        recyclerView?.layoutManager = gridLayoutManager
//        dialog = SpotsDialog(context)
//        loadWebsiteSource(false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val arrayList = ArrayList<Model>()
        backGroundColor()
        Paper.init(context) //Init cache Db
        mService = Common.newsService //InitService
//        val rect = Rect()
//        swipeTorefresh?.getDrawingRect(rect)

        swipeTorefresh?.setOnRefreshListener { //what to do on the refresh option
            loadWebsiteSource(true)
        }
        recyclerView?.setHasFixedSize(true)
        for(i in 1..10) {
            val gridLayoutManager =
                GridLayoutManager(context, 1, LinearLayoutManager.HORIZONTAL, false)
            recyclerView?.layoutManager = gridLayoutManager
        }
            dialog = SpotsDialog(context, R.style.Custom)
        loadWebsiteSource(false)
        arrayList.add(
            Model(
                "SPORTS",
                R.drawable.ic_baseline_directions_bike_24,
                //Color.parseColor("#004742"),
                R.drawable.background2,
                Color.parseColor("#ffffff")//#ebe8cf
            ,"sports"
            )
        )
        arrayList.add(
            Model(
                "GENERAL",
                R.drawable.ic_baseline_language_24,
                //Color.parseColor("#1e665b"),
                R.drawable.background3,
                Color.parseColor("#ffffff"),
                "general"
            )
        )
        arrayList.add(
            Model(
                "BUSINESS",
                R.drawable.ic_euro_symbol_black_24dp,
                //Color.parseColor("#1e665b"),
                R.drawable.background3,
                Color.parseColor("#ffffff"),
                "business"
            )
        )
        arrayList.add(
            Model(
                "ENTERTAINMENT",
                R.drawable.ic_videocam_black_24dp,
                //Color.parseColor("#004742"),
                R.drawable.background2,
                Color.parseColor("#ffffff"),
                "entertainment"
            )
        )
        arrayList.add(
            Model(
                "HEALTH",
                R.drawable.ic_baseline_healing_24,
                //Color.parseColor("#004742"),
                R.drawable.background2,
                Color.parseColor("#ffffff"),
                "health"
            )
        )
        arrayList.add(
            Model(
                "SCIENCE",
                R.drawable.ic_baseline_import_contacts_24,
                //Color.parseColor("#1e665b"),
                R.drawable.background3,
                Color.parseColor("#ffffff"),"science"
            )
        )
        arrayList.add(
            Model(
                "TECHNOLOGY",
                R.drawable.ic_baseline_emoji_objects_24,
                //Color.parseColor("#1e665b"),
                R.drawable.background3,
                Color.parseColor("#ffffff"),"technology"
            )
        )
        val modelAdapter = context?.let {
            ModelAdapter(
                arrayList,
                it
            )
        } // this means that the this all things should be implemented here
        val gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        recyclerView3?.layoutManager = gridLayoutManager // here the view is attached to recycler view that means this should be implemented inside the recycler view
        recyclerView3?.adapter = modelAdapter
    }
    fun loadWebsiteSource(isRefresh:Boolean){
        if(!isRefresh){
            val cache = Paper.book().read<String>("cache")
            if(cache!= null && !cache.isBlank() && cache != "null"){
                //read cache
                val webSite = Gson().fromJson(cache,WebSite::class.java)
                adapter = ListSourceAdapter(webSite, context!!)
                adapter.notifyDataSetChanged()
                recyclerView?.adapter = adapter
            }else{
                //Load website and write cache
                dialog.show()
                //fetch new data
                mService.sources.enqueue(object :Callback<WebSite>{
                    override fun onFailure(call: Call<WebSite>, t: Throwable) {
                        Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<WebSite>, response: Response<WebSite>) {
                        adapter = ListSourceAdapter(response.body()!!,context!!)
                        adapter.notifyDataSetChanged()
                        recyclerView?.adapter = adapter
                        //save to cache
                        Paper.book().write("cache",Gson().toJson(response.body()))
                        dialog.dismiss()
                    }

                })
            }
        }
        else{
            swipeToRefresh.isRefreshing = true //when the refreshing icon rotates case for that
            mService.sources.enqueue(object :Callback<WebSite>{
                override fun onFailure(call: Call<WebSite>, t: Throwable) {
                    Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<WebSite>, response: Response<WebSite>) {
                    adapter = ListSourceAdapter(response.body()!!,context!!)
                    adapter.notifyDataSetChanged()
                    recyclerView?.adapter = adapter
                    //save to cache
                    Paper.book().write("cache",Gson().toJson(response.body()))
                    swipeTorefresh?.isRefreshing = false
                }

            })

        }
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun backGroundColor() {
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        activity?.window?.navigationBarColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        activity?.window?.setBackgroundDrawableResource(R.drawable.background)
    }
//    fun showErrorMessage(imageView:Int,title:String,message:String){
//        if(relativeLayout!!.visibility == View.GONE){
//            relativeLayout!!.visibility = View.VISIBLE
//        }
//        imageView.setImageResource
   // }





}