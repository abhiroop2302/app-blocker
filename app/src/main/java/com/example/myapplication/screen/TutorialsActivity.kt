package com.example.myapplication.screen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.example.custom_tutorials.PagePosListener
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityTutorialsBinding
import com.example.myapplication.tutorials.BaseTutorialFragment
import com.example.myapplication.tutorials.TutorialFragment1
import com.example.myapplication.tutorials.TutorialFragment2
import com.example.myapplication.tutorials.TutorialFragment3
import com.example.myapplication.tutorials.TutorialFragment4
import com.example.myapplication.tutorials.viewpager.CustomViewPagerManager




class TutorialsActivity : AppCompatActivity(), PagePosListener {

    private lateinit var binding: ActivityTutorialsBinding
    private val fragmentList: ArrayList<BaseTutorialFragment> = ArrayList()
    private val indicatorList: ArrayList<Int> = ArrayList()
    private lateinit var viewPagerManager: CustomViewPagerManager


    private var firstTime = false
    private var loadTutorial = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        instance = this
        initListenerAndList()
    }

    /**
     *  function to initialise the list and set the click listeners for the different views.
     */
    private fun initListenerAndList() {
        initIndicatorList()
        initFragments()
        initViewPager()
        setClickListener()
    }

    private fun setClickListener() {
        binding.fabNext.setOnClickListener {
            if (viewPagerManager.isLastPage) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                return@setOnClickListener
            }
            viewPagerManager.setCurrentItem(viewPagerManager.activePage + 1)
        }
    }

    /**
     *  this is the indicator list which takes care for the indicator view shown at the bottom for the tutorials screen.
     */
    private fun initIndicatorList() {
        indicatorList.add(R.drawable.ic_tutorial_indicator_1)
        indicatorList.add(R.drawable.ic_tutorial_indicator_2)
        indicatorList.add(R.drawable.ic_tutorial_indicator_3)
        indicatorList.add(R.drawable.ic_tutorial_indicator_4)
    }

    private fun initViewPager() {
        viewPagerManager = CustomViewPagerManager(
            supportFragmentManager,
            fragmentList,
            binding.vpTutorial,
            this
        )
    }

    /**
     *  this function contains the list for the fragments to be used on the view pager.
     */
    private fun initFragments() {
        fragmentList.add(TutorialFragment1.getInstance())
        fragmentList.add(TutorialFragment2.getInstance())
        fragmentList.add(TutorialFragment3.getInstance())
        fragmentList.add(TutorialFragment4.getInstance())
    }

    private fun toggleIndicator(activePage: Int) {
        binding.ivIndicator.setImageResource(indicatorList[activePage])
    }

    private fun loadTutorial(fragment: BaseTutorialFragment) {
        fragment.loadTutorialWithAnimation()
    }

    /**
     *  this callback is used in the case when user swipe action performed on the viewpager ,
     *  Here we do :
     *
     *  1.) for the first time it load the view pager it is only called then we call the load tutorial method over here.
     *  2.) Secondly we are use this callback to change the indication view on the bottom of the screen to show the page.
     */
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        loadInitialPageForFirstTime()

        if (viewPagerManager.isLastPage) {
            binding.fabNext.setImageResource(R.drawable.ic_done)
            toggleIndicator(viewPagerManager.activePage)
            return
        }
        toggleIndicator(viewPagerManager.activePage)
        binding.fabNext.setImageResource(R.drawable.ic_next)
    }

    /**
     *  this function will only execute for the first time the activity loads the view pager.
     */
    private fun loadInitialPageForFirstTime() {
        if (firstTime)
            return

        firstTime = true
        loadTutorial(fragmentList[0])

    }


    /**
     *  this callback is used to make sure to load the signal to the fragment to start the animation for the views.
     */
    override fun onPageSelected(position: Int) {

        if(!loadTutorial)
            return

        loadTutorial(fragmentList[position])
        resetViewForPreviousFragment()
    }

    private fun resetViewForPreviousFragment() {
        if(viewPagerManager.lastActivePage == -1)
            return
        fragmentList[viewPagerManager.lastActivePage].resetView()
    }

    override fun onPageScrollStateChanged(state: Int) {}


    companion object {
        private const val loggerTag = "TutorialsActivity"
        private var instance:TutorialsActivity?= null
        fun getContext():Context?{
            return instance?.applicationContext
        }
    }

}