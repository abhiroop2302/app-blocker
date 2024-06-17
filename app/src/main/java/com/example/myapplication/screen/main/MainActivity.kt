package com.example.myapplication.screen.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.abstract_managers.BaseRecyclerView
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.PopUpMenuBinding
import com.example.myapplication.manager.AppBarManager
import com.example.myapplication.manager.NavigationManager
import com.example.myapplication.manager.PopUpWindowManager
import com.example.myapplication.screen.add_block_section.AddRestrictionActivity
import com.example.myapplication.screen.faq_section.FAQActivity
import com.example.myapplication.screen.notification_section.BlockedNotificationActivity
import com.example.myapplication.screen.settings_section.SettingsActivity
import com.example.myapplication.screen.upgrade_section.UpgradeActivity
import com.example.myapplication.screen.upgrade_section.UpgradeFeatureRecyclerImplementor
import com.example.myapplication.utility.setStatusBar


class MainActivity :
    AppCompatActivity(),
    NavigationManager.TabChangeListener, AppBarManager.AppBarOptionListener{

    init {
        instance = this
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var navigationManager: NavigationManager
    private lateinit var appBarManager: AppBarManager
    private lateinit var popUpWindowManager: PopUpWindowManager

    override fun onCreate(savedInstanceState: Bundle?) {

        window.setStatusBar(this, ContextCompat.getColor(this, R.color.white))
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
    }

    /**
     *  here we will initialize the different managers for the main screen.
     */
    private fun initialize() {
        appBarManager = AppBarManager(binding.appBar, this)
        popUpWindowManager = PopUpWindowManager(PopUpMenuBinding.inflate(layoutInflater), this)
        navigationManager = NavigationManager(binding.bottomBar, supportFragmentManager, this)
    }

    /**
     *  callback function when any tab is clicked or selected by the user from the bottom nav bar
     *  except the 'Add Restriction tab (floating action button click)'
     */
    override fun onTabChanged(appBarTitle: String) {
        appBarManager.changeAppBarTitle(appBarTitle)
    }

    /**
     *  callback option when user click on the different option from the app bar of the screen.
     */
    override fun onOptionClicked(navigator: AppBarManager.Navigator) {
        val activity = when (navigator) {
            AppBarManager.Navigator.Upgrade -> UpgradeActivity::class.java
            AppBarManager.Navigator.Setting -> SettingsActivity::class.java
            AppBarManager.Navigator.FAQ -> FAQActivity::class.java
            AppBarManager.Navigator.Notification -> BlockedNotificationActivity::class.java
        }
        startActivity(Intent(this, activity))
    }

    /**
     *  called when the user click on the more option icon from the app bar of the screen.
     */
    override fun showPopUpWindow(view: View) {
        popUpWindowManager.showPopUpWindow(view)
    }

    /**
     *  function when the user click on the add restriction (floating action button)
     *  from the bottom nav bar screen.
     */
    override fun addRestriction() {
        startActivity(Intent(this, AddRestrictionActivity::class.java))
    }


    /**
     *  function is very very important , because in fragments attached to this activity ,
     *  sometime we need the requireActivity, function -> they can throw fragment not attached to
     *  the context , so in that case , we will use the activity (parent activity ) there because fragment may not
     *  attached to context, but activity still alive there.
     */
    companion object {
        private lateinit var instance: MainActivity
        fun getInstance() = instance
    }
}