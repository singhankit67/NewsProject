package com.example.mydaiilynews.ui.main

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
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
    lateinit var mService:NewsService
    lateinit var adapter:ListSourceAdapter
    lateinit var dialog:AlertDialog
    var swipeTorefresh:SwipeRefreshLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view?.findViewById(R.id.recyclerView)
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
        //val arrayList = ArrayList<Model>()
        backGroundColor()
        Paper.init(context) //Init cache Db
        mService = Common.newsService //InitService
//        val rect = Rect()
//        swipeTorefresh?.getDrawingRect(rect)

        swipeTorefresh?.setOnRefreshListener { //what to do on the refresh option
            loadWebsiteSource(true)
        }
        recyclerView?.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        dialog = SpotsDialog(context)
        loadWebsiteSource(false)


//        fun loadWebsitesSources() {
//            dialog?.show()
//            apiInterface?.sources?.enqueue(object: Callback<WebSite> {
//                override fun onResponse(call: Call<WebSite>, response: Response<WebSite>) {
//                    dialog?.dismiss()
//                    val webSite = response.body()
//                    if (webSite != null && response.body()!!.sources?.size!! > 0)
//                    {
//                        listSources?.clear()
//                        listSources?.addAll(webSite.sources!!)
//                    }
//                    else
//                    {
//                        Toast.makeText(context, "No sources found", Toast.LENGTH_LONG).show()
//                    }
//                    adapter?.notifyDataSetChanged()
//                }
//                override fun onFailure(call:Call<WebSite>, t:Throwable) {
//                    Toast.makeText(context, "Error:" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show()
//                    dialog?.dismiss()
//                }
//            })
//        }
//        fun init() {
//            recyclerView?.setHasFixedSize(true)
//            val gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
//            recyclerView?.layoutManager = gridLayoutManager
//            dialog = SpotsDialog(context,"Loading..")
//            dialog!!.show()
//            apiInterface = apiClient.getApiClient()//(ApiInterface::class.java)
//            listSources = ArrayList()
//            adapter = SourcesAdapter(context!!, listSources as ArrayList<Source>)
//            recyclerView?.adapter = adapter
//        }
//        arrayList.add(
//            Model(
//                "SPORTS",
//                R.drawable.ic_directions_run_black_24dp,
//                //Color.parseColor("#004742"),
//                R.drawable.bg_gradient,
//                Color.parseColor("#ffffff"),//#ebe8cf
//                Color.parseColor("#64FF1717")
//            )
//        )
//        arrayList.add(
//            Model(
//                "START-UPS",
//                R.drawable.ic_trending_up_black_24dp,
//                //Color.parseColor("#1e665b"),
//                R.drawable.bg_gradient,
//                Color.parseColor("#ffffff"),
//                Color.parseColor("#64FF1717")
//            )
//        )
//        arrayList.add(
//            Model(
//                "BUSINESS",
//                R.drawable.ic_supervisor_account_black_24dp,
//                //Color.parseColor("#1e665b"),
//                R.drawable.bg_gradient,
//                Color.parseColor("#ffffff"),
//                Color.parseColor("#004742")
//            )
//        )
//        arrayList.add(
//            Model(
//                "ENTERTAINMENT",
//                R.drawable.ic_videocam_black_24dp,
//                //Color.parseColor("#004742"),
//                R.drawable.bg_gradient,
//                Color.parseColor("#ffffff"),
//                Color.parseColor("#64FF1717")
//            )
//        )
//        arrayList.add(
//            Model(
//                "POLITICS",
//                R.drawable.ic_euro_symbol_black_24dp,
//                //Color.parseColor("#004742"),
//                R.drawable.bg_gradient,
//                Color.parseColor("#ffffff"),
//                Color.parseColor("#64FF1717")
//            )
//        )
//        arrayList.add(
//            Model(
//                "FINANCE & ECONOMY",
//                R.drawable.ic_euro_symbol_black_24dp,
//                //Color.parseColor("#1e665b"),
//                R.drawable.bg_gradient,
//                Color.parseColor("#ffffff"),
//                Color.parseColor("#64FF1717")
//            )
//        )
//        arrayList.add(
//            Model(
//                "INTERNATIONAL",
//                R.drawable.ic_public_black_24dp,
//                //Color.parseColor("#1e665b"),
//                R.drawable.bg_gradient,
//                Color.parseColor("#ffffff"),
//                Color.parseColor("#64FF1717")
//            )
//        )
//        arrayList.add(
//            Model(
//                "AUTOMOBILE",
//                R.drawable.ic_directions_car_black_24dp,
//                //Color.parseColor("#004742"),
//                R.drawable.bg_gradient,
//                Color.parseColor("#ffffff"),
//                Color.parseColor("#64FF1717")
//            )
//        )
//        arrayList.add(
//            Model(
//                "FASHION",
//                R.drawable.ic_content_cut_black_24dp,
//                //Color.parseColor("#004742"),
//                R.drawable.bg_gradient,
//                Color.parseColor("#ffffff"),
//                Color.parseColor("#64FF1717")
//            )
//        )
//        arrayList.add(
//            Model(
//                "EDUCATION",
//                R.drawable.ic_collections_bookmark_black_24dp,
//                //Color.parseColor("#1e665b"),
//                R.drawable.bg_gradient,
//                Color.parseColor("#ffffff"),
//                Color.parseColor("#64FF1717")
//            )
//        )
//        val modelAdapter = context?.let {
//            ModelAdapter(
//                arrayList,
//                it
//            )
//        } // this means that the this all things should be implemented here
//        val gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
//        recyclerView?.layoutManager =
//            gridLayoutManager // here the view is attached to recycler view that means this should be implemented inside the recycler view
//        recyclerView?.adapter = modelAdapter
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





}