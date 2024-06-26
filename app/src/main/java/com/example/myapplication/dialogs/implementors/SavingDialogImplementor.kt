package com.example.myapplication.dialogs.implementors

import android.app.Dialog
import android.view.Window
import android.view.WindowManager
import androidx.viewbinding.ViewBinding
import com.example.myapplication.databinding.DialogSavingTimeBinding
import com.example.myapplication.dialogs.AppDialog
import com.example.myapplication.dialogs.DialogResultManager
import com.example.myapplication.dialogs.modals.EmailModal
import com.example.myapplication.screen.main.MainActivity

/**
 *  function for showing the rate us dialog to the user
 */
class SavingDialogImplementor : AppDialog{

    private lateinit var listener: DialogResultManager
    private lateinit var dialog :Dialog
    private lateinit var binding:DialogSavingTimeBinding

    private val activity:MainActivity by lazy {
        MainActivity.getInstance()
    }

    /**
     *  function to set the listener to the dialog view.
     */
    private fun setListeners() {
        binding.apply {
            tvCancel.setOnClickListener {
                listener.onResult("cancel clicked")
                dialog.dismiss()
            }
            tvRate.setOnClickListener {
                listener.onResult(EmailModal("feedback called"))
                dialog.dismiss()
            }
            tvFeedback.setOnClickListener {
                listener.onResult(EmailModal("rate us called"))
                dialog.dismiss()
            }
        }
    }

    /**
     *  function to set the content view for the dialog with setting the width and height for the dialog.
     */
    private fun initProperties(viewBinding: ViewBinding) {
        binding = viewBinding as DialogSavingTimeBinding
        dialog = Dialog(activity)
        dialog.apply {
            setCancelable(false)
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            setContentView(binding.root)
        }
        setParams()
    }

    /**
     *  function to set the custom width and height for the dialog.
     */
    private fun setParams() {
        val displayMetrics = activity.resources.displayMetrics
        val windowParams = dialog.window?.attributes

        windowParams?.apply {
            width = (displayMetrics.widthPixels * 0.85).toInt()
            height = WindowManager.LayoutParams.WRAP_CONTENT
        }

        dialog.window?.attributes = windowParams
    }

    /**
     *  override calls for the app dialog to make sure
     *  we can initialize the dialog give the necessary binding to the dialog
     *  and also receive the callback from the user.
     */
    override fun initializeDialog(viewBinding: ViewBinding)  {
        initProperties(viewBinding)
        setListeners()
    }
    override fun register(dialogResultManager: DialogResultManager) {
        listener = dialogResultManager
    }

    override fun show() {
        dialog.show()
    }

}