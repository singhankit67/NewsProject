package com.example.mydaiilynews.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mydaiilynews.ModelAdapter
import com.example.mydaiilynews.R
import java.util.ArrayList
import link.fls.swipestack.SwipeStack

@SuppressLint("Registered")
class RecentActivity : Fragment(), SwipeStack.SwipeStackListener, View.OnClickListener {

    private lateinit var mData:ArrayList<String>
    var myActivity: Activity? = null
    private lateinit var mSwipeStack:SwipeStack
    private var mAdapter:SwipeStackAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val  view = inflater.inflate(R.layout.fragment_recent,container,false)
        mSwipeStack = view?.findViewById(R.id.swipeStack) as SwipeStack
        //mData = ArrayList<String>()
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

            mData = ArrayList<String>()
            mAdapter = SwipeStackAdapter(mData)
        mSwipeStack.adapter = mAdapter
            mSwipeStack.setListener(this)
            fillWithTestData()
        }
        private fun fillWithTestData() {
            for (x in 0..4)
            {
                mData.add(getString(R.string.dummy_text) + " " + (x + 1))
            }
        }
        override fun onClick(v:View) {
//            if (v.equals(mButtonLeft))
//            {
//                mSwipeStack.swipeTopViewToLeft()
//            }
//            else if (v.equals(mButtonRight))
//            {
//                mSwipeStack.swipeTopViewToRight()
//            }
//            else if (v.equals(mFab))
//            {
//                mData.add(getString(R.string.dummy_fab))
                mAdapter?.notifyDataSetChanged()
            }

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
        override fun onViewSwipedToRight(position:Int) {
            //val swipedElement = mAdapter?.getItem(position)
            Toast.makeText(context,"Swiped to Right",Toast.LENGTH_SHORT).show()
        }
        override fun onViewSwipedToLeft(position:Int) {
            //val swipedElement = mAdapter?.getItem(position)
            Toast.makeText(context,"Swiped to Left",Toast.LENGTH_SHORT).show()
        }
        override fun onStackEmpty() {
            Toast.makeText(context,"List is empty", Toast.LENGTH_SHORT).show()
        }
        inner class SwipeStackAdapter(data:List<String>):BaseAdapter() {
            private val mData:List<String> = data
            override fun getItem(position:Int):String {
                return mData[position]
            }
            override fun getItemId(position:Int):Long {
                return position.toLong()
            }

            override fun getCount(): Int {
                return mData.size
            }

            @SuppressLint("ViewHolder")
            override fun getView(position:Int, convertView:View?, parent:ViewGroup):View {
                //var holder: ModelAdapter.ViewHolder
                var retView: View? = null
                if(convertView == null) {
                    //retView = vi.inflate(resource, null)
                    retView = layoutInflater.inflate(R.layout.card, parent, false)
                }
                val textViewCard = retView?.findViewById(R.id.Description) as TextView
                textViewCard.text = mData[position]
                return retView
            }
        }
    }