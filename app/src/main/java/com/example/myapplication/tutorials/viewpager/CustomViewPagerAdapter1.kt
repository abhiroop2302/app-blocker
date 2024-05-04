package com.example.myapplication.tutorials.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.tutorials.BaseTutorialFragment

/**
 *
 * custom view Pager which is used for the tutorial section ,
 * which is used to load the list for the fragments which
 * will be replaced when the user so the swipe Action.
 *
 */
class CustomViewPagerAdapter1(private val fragmentList:ArrayList<BaseTutorialFragment>, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    /**
     * function check is the current visible page to the user is the last page or not.
     * @param pos
     * @return
     */
    fun isLastPage(pos: Int): Boolean {
        return pos == fragmentList.size - 1
    }
}