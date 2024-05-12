package com.example.myapplication.manager

import android.util.Log
import android.view.View
import com.example.myapplication.databinding.AppBarBinding

/**
 *  this class handle with the app bar for the main activity screen.
 *  we will make it type of the generic if there exists the different app bars in app in future.
 */
class AppBarManager(
    private val binding: AppBarBinding,
    private val listener: AppBarOptionListener,
) {
    fun changeAppBarTitle(title: String) {
        binding.tvTitle.text = title
    }

    init {
        setClickListener()
    }

    private fun setClickListener() {
        binding.apply {
            tvUpgrade.setOnClickListener { listener.onOptionClicked(Navigator.Upgrade)}
            ivNotification.setOnClickListener { listener.onOptionClicked(Navigator.Notification)}
            ivMore.setOnClickListener {
                Log.d("infoLogger", "more option view is clicked.")
                listener.showPopUpWindow(it)
            }
        }
    }


    /**
     *  enum class take care of the case that when any option on the app bar is clicked , and where we have to navigate
     *  with the help of the listener.
     */
    enum class Navigator {
        Notification,
        Upgrade,
        FAQ,
        Setting
    }

    /**
     *  interface which take care for the app bar functionality and communicate to the main parent activity.
     */
    interface AppBarOptionListener{
        fun onOptionClicked(navigator: Navigator)
        fun showPopUpWindow(view: View)
    }


}