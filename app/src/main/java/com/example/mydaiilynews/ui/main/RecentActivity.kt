package com.example.mydaiilynews.ui.main

import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.mydaiilynews.*
import com.example.mydaiilynews.Adapter
import dmax.dialog.SpotsDialog
import java.util.ArrayList
import link.fls.swipestack.SwipeStack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("Registered")
class RecentActivity : Fragment() {
    var myActivity:Activity? = null
    var api_key = "b2f868ee29a64c3bb200636a22e70181"
    lateinit var mInterface:ApiInterface
    var viewPager3: ViewPager? = null
    lateinit var dialog1: SpotsDialog
    lateinit var query : EditText
    var imageButton : ImageButton? = null
    //var listOfArticles : MutableList<Article1>? = null
    lateinit var adapter:Adapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val  view = inflater.inflate(R.layout.fragment_recent,container,false)
        //mData = ArrayList<String>()
        query = view.findViewById(R.id.seachbar)
        viewPager3 = view.findViewById(R.id.view_pager3)
        imageButton = view.findViewById(R.id.searchButton)
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

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //val layoutManager1 = LinearLayoutManager(context)
        mInterface = ApiClient.apiInterface
        dialog1 = SpotsDialog(context,R.style.Custom)
//        recyclerView2.layoutManager = layoutManager1
//        recyclerView2.setHasFixedSize(true)
//        recyclerView2.itemAnimator = DefaultItemAnimator()
//        recyclerView2.isNestedScrollingEnabled = false
        LoadJson("")
        imageButton!!.setOnClickListener {
            if(!query.text.toString().equals("")){
                LoadJson(query.text.toString())
                query.isCursorVisible = false

            }else{
                LoadJson("")
            }
        }

    }
    fun LoadJson(query1:String){
        //val apiInterface = ApiClient().getApiClient()?.create(ApiInterface::class.java)
        //val country = Utils.getCountry()
        val call : Call<News1>
        if(!query.text.toString().equals("")){
             call = mInterface.getNews(ApiClient.getAllNews(query1))
        }
        else{
            call = mInterface.getNews(ApiClient.getTopHeadlines())
        }
        dialog1.show()
        call.enqueue(object :Callback<News1>{
            override fun onFailure(call: Call<News1>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<News1>, response: Response<News1>) {
                //if(response.isSuccessful && response.body()?.article != null){
                    //if(!listOfArticles!!.isEmpty()){
                        //listOfArticles!!.clear()
                    //}
                dialog1.dismiss()
                    val listOfArticles = response.body()!!.article!!
                    adapter = Adapter(listOfArticles,context!!)
                    viewPager3!!.adapter = adapter
                    adapter.notifyDataSetChanged()


                //}else{
                    //Toast.makeText(context,"lanat",Toast.LENGTH_SHORT).show()
                //}
            }
        })
    }

//            mData = ArrayList<String>()
//            mAdapter = SwipeStackAdapter(mData)
//        mSwipeStack.adapter = mAdapter
//            mSwipeStack.setListener(this)
//            fillWithTestData()
//        }
//        private fun fillWithTestData() {
//            for (x in 0..4)
//            {
//                mData.add(getString(R.string.dummy_text) + " " + (x + 1))
//            }
//        }
//        override fun onClick(v:View) {
////            if (v.equals(mButtonLeft))
////            {
////                mSwipeStack.swipeTopViewToLeft()
////            }
////            else if (v.equals(mButtonRight))
////            {
////                mSwipeStack.swipeTopViewToRight()
////            }
////            else if (v.equals(mFab))
////            {
////                mData.add(getString(R.string.dummy_fab))
//                mAdapter?.notifyDataSetChanged()
//            }

       // override fun onCreateOptionsMenu(menu:Menu):Boolean {
//            val inflater = getMenuInflater()
//            inflater.inflate(R.menu.main, menu)
//            return true
        //}
//        override fun onOptionsItemSelected(item:MenuItem):Boolean {
//            when (item.getItemId()) {
//                R.id.menuReset -> {
//                    mSwipeStack.resetStack()
//                    Snackbar.make(mFab, R.string.stack_reset, Snackbar.LENGTH_SHORT).show()
//                    return true
//                }
//                R.id.menuGitHub -> {
//                    val browserIntent = Intent(
//                        Intent.ACTION_VIEW, Uri.parse("https://github.com/flschweiger/SwipeStack"))
//                    startActivity(browserIntent)
//                    return true
//                }
//            }
//            return super.onOptionsItemSelected(item)
//        }
//        override fun onViewSwipedToRight(position:Int) {
//            //val swipedElement = mAdapter?.getItem(position)
//            Toast.makeText(context,"Swiped to Right",Toast.LENGTH_SHORT).show()
//        }
//        override fun onViewSwipedToLeft(position:Int) {
//            //val swipedElement = mAdapter?.getItem(position)
//            Toast.makeText(context,"Swiped to Left",Toast.LENGTH_SHORT).show()
//        }
//        override fun onStackEmpty() {
//            Toast.makeText(context,"List is empty", Toast.LENGTH_SHORT).show()
//        }
//        inner class SwipeStackAdapter(data:List<String>):BaseAdapter() {
//            private val mData:List<String> = data
//            override fun getItem(position:Int):String {
//                return mData[position]
//            }
//            override fun getItemId(position:Int):Long {
//                return position.toLong()
//            }
//
//            override fun getCount(): Int {
//                return mData.size
//            }
//
//            @SuppressLint("ViewHolder")
//            override fun getView(position:Int, convertView:View?, parent:ViewGroup):View {
//                //var holder: ModelAdapter.ViewHolder
//                var retView: View? = null
//                if(convertView == null) {
//                    //retView = vi.inflate(resource, null)
//                    retView = layoutInflater.inflate(R.layout.card, parent, false)
//                }
//                val textViewCard = retView?.findViewById(R.id.Description) as TextView
//                textViewCard.text = mData[position]
//                return retView
//            }
//        }
    }