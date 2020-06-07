package com.example.mydaiilynews.ui.main

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mydaiilynews.ListNews
import com.example.mydaiilynews.R
import androidx.fragment.app.Fragment as Fragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int) :Fragment {
        when (position) {
            0 -> {
                return HomeActivity()
            }
            1 -> {
                return RecentActivity()
            }
            else -> return AboutUsActivity()
        }
    }
    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "HOME"
            1 -> return "TOP-STORIES"
            2 -> return "ABOUT US"
        }
        return null
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 3
    }
}