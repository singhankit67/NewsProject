package com.example.mydaiilynews
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import dmax.dialog.SpotsDialog
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_list_news.*
import link.fls.swipestack.SwipeStack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListNews : AppCompatActivity(){
    var source = ""
    lateinit var dialog:AlertDialog
    lateinit var mService:NewsService
    //var swipeStack:SwipeStack? = null
    var viewPager2:ViewPager? = null
    var distance :Int= 200
    //var recyclerView1: RelativeLayout? = null
    var swipeToRefresh1:SwipeRefreshLayout? = null
    lateinit var adapter:ListNewsAdapter
    lateinit var query1: EditText
    var imageButton1:ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun backGroundColor() {
            window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window?.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
            window?.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent)
            window?.setBackgroundDrawableResource(R.drawable.background)
        }
        setContentView(R.layout.activity_list_news)
        backGroundColor()
        query1 = findViewById(R.id.searchbar2)
        mService = Common.newsService
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        imageButton1 = findViewById(R.id.searchButton2)
        dialog = SpotsDialog(this,R.style.Custom)
        viewPager2 = findViewById(R.id.view_pager2)
//        recyclerView1 = findViewById(R.id.relativeLayoutNoFound)
//        swipeStack = findViewById(R.id.swipeStack)

       // swipeStack!!.adapter = adapter
        //swipeStack!!.setListener(this)
        //recyclerView1!!.setHasFixedSize(true)
        //recyclerView1!!.layoutManager = LinearLayoutManager(this)
        //!!.itemAnimator = DefaultItemAnimator()
        //recyclerView1!!.isNestedScrollingEnabled =true
        //recyclerView1!!.adapter = adapter
        swipeToRefresh1 = findViewById(R.id.swipeToRefresh1)
        swipeToRefresh1!!.setDistanceToTriggerSync(distance)
        swipeToRefresh1!!.setOnRefreshListener { loadNews(source,true,"") }
        if (intent != null) {
            source = intent.getStringExtra("source")
            if(!source.isEmpty()) {
                loadNews(source, false,"")

            }

        }
        imageButton1!!.setOnClickListener {
            if(!query1.text.toString().equals("")){
                loadNews(source,true,query1.text.toString())
                swipeToRefresh1!!.setOnRefreshListener { loadNews(source,true,query1.text.toString()) }
                query1.isCursorVisible = false
            }else{
                loadNews(source,true,"")
                swipeToRefresh1!!.setOnRefreshListener { loadNews(source,true,"") }
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
    fun loadNews(source:String?,isRefreshed:Boolean,query2:String){
        if(isRefreshed){
            dialog.show()
            val call:Call<News>
            if(!query1.text.toString().equals("")){
                call = mService.getNewsFromSource(Common.getNewsApi2(query2))
            }else{
                call = mService.getNewsFromSource(Common.getNewsApi(source!!))
            }
            call.enqueue(object :Callback<News>{
                override fun onFailure(call: Call<News>, t: Throwable) {
                    Toast.makeText(this@ListNews,"Failed1",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<News>, response: Response<News>) {
                    dialog.dismiss()
                    //webHotUrl = response.body()?.articles?.get(0)?.url
                    val removeFirstItem = response.body()!!.articles
                    //removeFirstItem?.removeAt(0)
                    adapter = ListNewsAdapter(removeFirstItem!!,this@ListNews)
                    viewPager2!!.adapter = adapter
                    adapter.notifyDataSetChanged()
                    swipeToRefresh1?.isRefreshing = false
                    //initButton()

                }

            })
        }
        else{
            swipeToRefresh1!!.isRefreshing = true
            //dialog.show()
            mService.getNewsFromSource(Common.getNewsApi(source!!)).enqueue(object :Callback<News>{
                override fun onFailure(call: Call<News>, t: Throwable) {
                    Toast.makeText(this@ListNews,"Failed2", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<News>, response: Response<News>) {
                    swipeToRefresh1!!.isRefreshing =false
                    //webHotUrl = response.body()?.articles!![0].url
                    val removeFirstItem = response.body()!!.articles
                    //removeFirstItem!!.removeAt(0)
                    adapter = ListNewsAdapter(removeFirstItem!!,this@ListNews)
                    viewPager2!!.adapter = adapter
                    adapter.notifyDataSetChanged()
                    swipeToRefresh1!!.isRefreshing = false
                    //initButton()
                }

            })
        }

    }

//    override fun onViewSwipedToLeft(position: Int) {
//
//    }
//
//    override fun onViewSwipedToRight(position: Int) {
//
//    }
//
//    override fun onStackEmpty() {
//        swipeStack!!.visibility = View.GONE
//        recyclerView1!!.visibility = View.VISIBLE
//    }



}
