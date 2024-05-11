package com.example.myapplication.manager

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R
import com.example.myapplication.application.MainApplication
import com.example.myapplication.databinding.BottomNavBarBinding
import com.example.myapplication.screen.add_block_section.RestrictionFragment
import com.example.myapplication.screen.app_usage_section.AppUsageFragment
import com.example.myapplication.screen.archive_section.ArchiveFragment
import com.example.myapplication.screen.block_section.BlockSectionFragment
import com.example.myapplication.screen.reports_section.ReportsFragment
import com.example.myapplication.utility.toggleTabColor
import com.google.android.material.textview.MaterialTextView

class NavigationManager(
    private val binding: BottomNavBarBinding,
    private val fragmentManager: FragmentManager
) {

    private val selectedIconList: ArrayList<Int> = ArrayList()
    private val unselectedIconList: ArrayList<Int> = ArrayList()
    private val screenList: ArrayList<Fragment> = ArrayList()
    private val tabList: ArrayList<TabView> = ArrayList()

    private var activePos = 0

    init {
        initialize()
        setTabListener()
    }

    private fun setTabListener() {
        for (i in tabList.indices){
            tabList[i].rootView().setOnClickListener {
                updateTab(i)
            }
        }

        binding.fabAdd.setOnClickListener {
            doNavigation(screenList.size - 1 )
        }
    }

    private fun updateTab(pos: Int) {
        if(activePos == pos)
            return

        updateTabView(activePos, false)
        updateTabView(pos, true)
        doNavigation(pos)
        activePos = pos
    }

    private fun doNavigation(pos: Int) {
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, screenList[pos])
            commit()
        }
    }

    private fun updateTabView(tabPos: Int, isSelected: Boolean) {
        tabList[tabPos].infoView().toggleTabColor(isSelected)
        tabList[tabPos].iconView().setImageResource(updateIcon(tabPos, isSelected))

    }

    private fun updateIcon(tabPos: Int, selected: Boolean): Int {
        return when(selected){
            true-> selectedIconList[tabPos]
            else -> unselectedIconList[tabPos]
        }
    }


    private fun initialize() {
        initTabList()
        initSelectedIconList()
        initUnselectedIconList()
        initScreenList()
    }

    private fun initScreenList() {
        screenList.apply {
            add(BlockSectionFragment())
            add(AppUsageFragment())
            add(ReportsFragment())
            add(ArchiveFragment())
            add(RestrictionFragment())
        }
    }

    private fun initUnselectedIconList() {

        unselectedIconList.apply {
            add(R.drawable.ic_nav_lock_unselected)
            add(R.drawable.ic_nav_hour_glass_unselected)
            add(R.drawable.ic_nav_reports_unselected)
            add(R.drawable.ic_nav_archive_unselected)
        }

    }

    private fun initSelectedIconList() {

        selectedIconList.apply {
            add(R.drawable.ic_nav_lock_selected)
            add(R.drawable.ic_nav_hour_glass_selected)
            add(R.drawable.ic_nav_reports_selected)
            add(R.drawable.ic_nav_archive_selected)
        }

    }

    private fun initTabList() {
        binding.apply {
            tabList.apply {
                add(TabView(llBlock, ivNavLock, tvLock))
                add(TabView(llAppUsage, ivNavAppUsage, tvAppUsage))
                add(TabView(llReports, ivNavReports, tvReports))
                add(TabView(llArchive,ivNavArchive, tvArchive))
            }
        }
    }


    inner  class TabView(
        private val tabParentView : LinearLayout,
        private val tabIconView:AppCompatImageView,
        private val tabInfoView:MaterialTextView
    ){
        fun rootView() = tabParentView
        fun infoView() = tabInfoView
        fun iconView() = tabIconView
    }


}