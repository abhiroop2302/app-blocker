package com.example.myapplication.manager

import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R
import com.example.myapplication.databinding.BottomNavBarBinding
import com.example.myapplication.screen.app_usage_section.AppUsageFragment
import com.example.myapplication.screen.archive_section.ArchiveFragment
import com.example.myapplication.screen.block_section.BlockSectionFragment
import com.example.myapplication.screen.reports_section.ReportsFragment
import com.example.myapplication.utility.toggleTabColor
import com.google.android.material.textview.MaterialTextView

/**
 *  This class is responsible for the navigation flow for the bottom nav bar in the main screen.
 *
 *  Logic Used -> I have created the list for the all tabs with the necessary properties which is used ,
 *  user select/unselect the tab , now i will take care of the valid tab click i have store the previous tab
 *  click by the user in the (active tab bit , by default = 0  which means 1st tab is selected), it will only change
 *  user click on the tab whose (position != active tab bit)
 *
 */
class NavigationManager(
    private val binding: BottomNavBarBinding,
    private val fragmentManager: FragmentManager,
    private val tabChangeListener: TabChangeListener
) {
    private val tabModal: ArrayList<NavItemModal> = ArrayList()

    private var activePos = 0

    init {
        initialize()
        setTabListener()
    }

    private fun setTabListener() {
        for (i in tabModal.indices) {
            tabModal[i].tabParentView.setOnClickListener {
                updateTab(i)
            }
        }

        binding.fabAdd.setOnClickListener {
            tabChangeListener.addRestriction()
        }
    }

    private fun updateTab(pos: Int) {
        if (activePos == pos)
            return

        updateTabView(activePos, false)
        updateTabView(pos, true)
        doNavigation(pos)
        tabChangeListener.onTabChanged(tabModal[pos].tabInfoView.text.trim().toString())
        activePos = pos
    }

    private fun doNavigation(pos: Int) {
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, tabModal[pos].screenView)
            commit()
        }
    }

    private fun updateTabView(tabPos: Int, isSelected: Boolean) {
        tabModal[tabPos].tabInfoView.toggleTabColor(isSelected)
        tabModal[tabPos].tabIconView.setImageResource(updateIcon(tabPos, isSelected))

    }

    private fun updateIcon(tabPos: Int, selected: Boolean): Int {
        return when (selected) {
            true -> tabModal[tabPos].selectedIcon
            else -> tabModal[tabPos].unselectedIcon
        }
    }


    private fun initialize() {
        initTabModal()
    }

    private fun initTabModal() {
        binding.apply {
            tabModal.apply {
                add(
                    NavItemModal(
                        llBlock,
                        ivNavLock,
                        tvLock,
                        R.drawable.ic_nav_lock_selected,
                        R.drawable.ic_nav_lock_unselected,
                        BlockSectionFragment()
                    )
                )

                add(
                    NavItemModal(
                        llAppUsage,
                        ivNavAppUsage,
                        tvAppUsage,
                        R.drawable.ic_nav_hour_glass_selected,
                        R.drawable.ic_nav_hour_glass_unselected,
                        AppUsageFragment()
                    )
                )
                add(
                    NavItemModal(
                        llReports,
                        ivNavReports,
                        tvReports,
                        R.drawable.ic_nav_reports_selected,
                        R.drawable.ic_nav_reports_unselected,
                        ReportsFragment()
                    )
                )

                add(
                    NavItemModal(
                        llArchive,
                        ivNavArchive,
                        tvArchive,
                        R.drawable.ic_nav_archive_selected,
                        R.drawable.ic_nav_archive_unselected,
                        ArchiveFragment()
                    )
                )
            }
        }
    }

    /**
     *  interface listen to the events when the any tab from the bottom nav view is selected by the user.
     */

    interface TabChangeListener {
        fun onTabChanged(appBarTitle: String)
        fun addRestriction()
    }

}

/**
 *  custom class modal for the navigation tab view , helpful because if
 *  we have to add something in the tab property then we can add it over here
 *  to make sure we can create the only one list.
 */
data class NavItemModal(
    val tabParentView: LinearLayout,
    val tabIconView: AppCompatImageView,
    val tabInfoView: MaterialTextView,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val screenView: Fragment
)
