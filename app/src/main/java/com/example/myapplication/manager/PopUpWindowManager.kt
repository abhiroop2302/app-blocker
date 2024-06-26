package com.example.myapplication.manager

import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.application.MainApplication
import com.example.myapplication.databinding.PopUpMenuBinding

/**
 *  This manager class is responsible to show the pop up menu when user click on the more option icon
 *  on the main screen , will create the generic class for this one if there exist the case where we
 *  have the different pop window in app there.
 *
 *  Small Hint ->  I can use the delegation to achieve the generic code for the different activity.
 *
 */
class PopUpWindowManager (
    private val binding: PopUpMenuBinding,
    private val listener: AppBarManager.AppBarOptionListener
){
    private var  popupWindow:PopupWindow = PopupWindow(binding.root)

    init {
        initDisplayMetrics()
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.apply {
            tvHelp.setOnClickListener {
                listener.onOptionClicked(AppBarManager.Navigator.FAQ)
                popupWindow.dismiss()
            }

            tvSettings.setOnClickListener {
                listener.onOptionClicked(AppBarManager.Navigator.Setting)
                popupWindow.dismiss()
            }

            tvSave.setOnClickListener {
                listener.showDialog()
                popupWindow.dismiss()
            }

        }
    }

    private fun initDisplayMetrics() {

        // This is important if we give the width wrap , then no pop up window will be shown ,
        // and if we give the width to the match parent then it will cover the whole activity view
        // so we need to give the width percentage by ourself.

        val displayMetrics = MainApplication.getInstance().applicationContext.resources.displayMetrics
        popupWindow.width = (displayMetrics.widthPixels * 0.6).toInt()
        popupWindow.height = ViewGroup.LayoutParams.WRAP_CONTENT

        popupWindow.isOutsideTouchable = true

        popupWindow.setBackgroundDrawable(
            ContextCompat.getDrawable(
                MainApplication.getInstance().applicationContext,
            R.drawable.bg_pop_up_menu)
        )

    }

    fun showPopUpWindow(view: View){
        popupWindow.showAsDropDown(view)
    }
}