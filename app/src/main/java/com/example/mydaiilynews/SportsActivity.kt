package com.example.mydaiilynews

import android.app.AlertDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton

import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_sports.*
import link.fls.swipestack.SwipeStack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SportsActivity : AppCompatActivity() {
    var api_key = "b2f868ee29a64c3bb200636a22e70181"
    lateinit var mInterface1: ApiInterface

    //var listOfArticles : MutableList<Article1>? = null
    lateinit var dialog: AlertDialog

    //var swipeStack1:SwipeStack ?= null
    var view_pager1: ViewPager? = null
    lateinit var query2: EditText
    lateinit var mService: NewsService
    lateinit var adapter2: ListNewsAdapter
    var imageButton2: ImageButton? = null
    var swipeToRefresh2: SwipeRefreshLayout? = null
    var url = ""

    //lateinit var recyclerView4: RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun backGroundColor() {
            window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window?.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
            window?.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent)
            window?.setBackgroundDrawableResource(R.drawable.background)
        }
        setContentView(R.layout.activity_sports)
        backGroundColor()
        //recyclerView4 = findViewById(R.id.relativeLayoutNoFound1)
        //swipeStack1 = findViewById(R.id.swipeStack1)
        query2 = findViewById(R.id.searchbar)
        mService = Common.newsService
        imageButton2 = findViewById(R.id.searchButton)
        swipeToRefresh2 = findViewById(R.id.swipeToRefresh2)
        dialog = SpotsDialog(this, R.style.Custom)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        view_pager1 = findViewById(R.id.view_pager1)
        //val layoutManager1 = LinearLayoutManager(this)
        mInterface1 = ApiClient.apiInterface
        swipeToRefresh2!!.setOnRefreshListener { LoadJson1(url, "", true) }
//        recyclerView4.layoutManager = layoutManager1
//        recyclerView4.setHasFixedSize(true)
//        recyclerView4.itemAnimator = DefaultItemAnimator()
//        recyclerView4.isNestedScrollingEnabled = false
        if (intent != null) {
            url = intent.getStringExtra("url")
            if (!url.isEmpty()) {
                LoadJson1(url, "", false)
            }

        }
        imageButton2!!.setOnClickListener {
            if (!query2.text.toString().equals("")) {
                LoadJson1(url, query2.text.toString(),true)
                swipeToRefresh2!!.setOnRefreshListener {
                    LoadJson1(
                        url,
                        query2.text.toString(),
                        true
                    )
                }
                query2.isCursorVisible = false

            } else {
                LoadJson1(url, "",true)
                swipeToRefresh2!!.setOnRefreshListener { LoadJson1(url, "", true) }
            }
        }
    }

    fun LoadJson1(url: String, query3: String, isRefreshed: Boolean) {
        //val apiInterface = ApiClient().getApiClient()?.create(ApiInterface::class.java)
        //val country = Utils.getCountry()
        if (isRefreshed) {
            val call: Call<News>
            if (!query2.text.toString().equals("")) {
                call = mService.getNewsFromSource(Common.getNewsApi2(query3))
            } else {
                call = mService.getNewsFromSource(Common.getNewsApi1(url))
            }
            dialog.show()
            call.enqueue(object : Callback<News> {
                override fun onFailure(call: Call<News>, t: Throwable) {
                    Toast.makeText(this@SportsActivity, "Failed3", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<News>, response: Response<News>) {
                    //if(response.isSuccessful && response.body()?.article != null){
                    //if(!listOfArticles!!.isEmpty()){
                    //listOfArticles!!.clear()
                    //}
                    dialog.dismiss()
                    val listOfArticles = response.body()!!.articles!!
                    adapter2 = ListNewsAdapter(listOfArticles, this@SportsActivity)
                    view_pager1!!.adapter = adapter2
                    adapter2.notifyDataSetChanged()
                    swipeToRefresh2!!.isRefreshing = false


                    //}else{
                    //Toast.makeText(context,"lanat",Toast.LENGTH_SHORT).show()
                    //}
                }
            })
        } else {
            swipeToRefresh2!!.isRefreshing = true
            mService.getNewsFromSource(Common.getNewsApi1(url)).enqueue(object : Callback<News> {
                override fun onFailure(call: Call<News>, t: Throwable) {
                    Toast.makeText(this@SportsActivity, "Failed3", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<News>, response: Response<News>) {
                    //if(response.isSuccessful && response.body()?.article != null){
                    //if(!listOfArticles!!.isEmpty()){
                    //listOfArticles!!.clear()
                    //}
                    dialog.dismiss()
                    val listOfArticles = response.body()!!.articles!!
                    adapter2 = ListNewsAdapter(listOfArticles, this@SportsActivity)
                    view_pager1!!.adapter = adapter2
                    adapter2.notifyDataSetChanged()
                    swipeToRefresh2!!.isRefreshing = false


                    //}else{
                    //Toast.makeText(context,"lanat",Toast.LENGTH_SHORT).show()
                    //}
                }
            })
        }

//    override fun onViewSwipedToLeft(position: Int) {
//    }
//
//    override fun onViewSwipedToRight(position: Int) {
//    }
//
//    override fun onStackEmpty() {
//        swipeStack1!!.visibility = View.GONE
//        recyclerView4.visibility = View.VISIBLE
//    }

    }
}