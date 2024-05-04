package com.example.myapplication.tutorials.viewpager

import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.custom_tutorials.PagePosListener
import com.example.myapplication.tutorials.BaseTutorialFragment


/**
 * custom view Pager class is used for the handling the tutorial screen operations.
 */
class CustomViewPagerManager : OnPageChangeListener {
    private var viewPager: ViewPager
    private var viewPagerAdapter: CustomViewPagerAdapter1? = null
    private var pagePosListener: PagePosListener? = null

    /**
     * getter function to get the current active page
     */
    var activePage = -1
        private set

    var lastActivePage = -1

    /**
     * constructor which is used to initialize the view pager cases where user does not want to get the page scroll
     * listener callback from the view pager.
     *
     * Case to be used -> where the activity is used , having set of the view pager (not for tutorial segment).
     */
    constructor(
        fragmentManager: FragmentManager,
        fragments: ArrayList<BaseTutorialFragment>,
        viewPager: ViewPager
    ) {
        this.viewPager = viewPager
        initializeViewPager(fragmentManager, fragments)
    }

    /**
     * constructor which is used to initialize the view pager case where apps consist of the tutorial section
     * in this case we need the page scrolled callback to make sure to address the user that this is the
     * last page for tutorial section
     */
    constructor(
        fragmentManager: FragmentManager,
        fragments: ArrayList<BaseTutorialFragment>,
        viewPager: ViewPager,
        pagePosListener: PagePosListener?
    ) {
        this.viewPager = viewPager
        this.pagePosListener = pagePosListener
        viewPager.setOnPageChangeListener(this)
        initializeViewPager(fragmentManager, fragments)
    }

    /**
     * function to initialize the adapter and bind it with the pager adapter.
     */
    private fun initializeViewPager(
        fragmentManager: FragmentManager,
        fragments: ArrayList<BaseTutorialFragment>
    ) {
        viewPagerAdapter = CustomViewPagerAdapter1(fragments,fragmentManager)
        viewPager.adapter = viewPagerAdapter
    }

    val isLastPage: Boolean
        /**
         * function to check is the current page is the last page.
         */
        get() = viewPagerAdapter?.isLastPage(activePage) == true

    /**
     * function to set the current fragment for the view pager which is used to change the fragment without swipe.
     */
    fun setCurrentItem(pos: Int) {
        activePage = pos
        viewPager.currentItem = activePage
    }

    /**
     * function for the page scrolled callback.
     */
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        lastActivePage = activePage
        activePage = position
        pagePosListener?.onPageScrolled(position, positionOffset, positionOffsetPixels)
    }

    /**
     * function for the page selected callback.
     */
    override fun onPageSelected(position: Int) {
        pagePosListener?.onPageSelected(position)
    }

    /**
     * function for the page scrolled state  callback.
     */
    override fun onPageScrollStateChanged(state: Int) {
        pagePosListener?.onPageScrollStateChanged(state)
    }

}