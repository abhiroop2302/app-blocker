package com.example.myapplication.bottom_sheet_dialogs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.myapplication.abstract_managers.GenericRecyclerView
import com.example.myapplication.databinding.LayoutPermissionListBinding
import com.example.myapplication.databinding.LayoutPermissionViewBinding
import com.example.myapplication.recycler_view_data_modals.PermissionModal
import com.example.myapplication.recycler_view_implementors.PermissionRecyclerViewImplementor
import com.example.myapplication.utility.addDefaultPermission
import com.example.myapplication.utility.runOnMainThread
import com.example.myapplication.utility.visibility
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 *  This class is the dialog class which is used to show the list of the necessary permission to user.
 */
class PermissionDialog(
    private val nonNullContext: Context,
    private val permissionModal:ArrayList<PermissionModal>,
    private val intentCallback:(Intent) -> Unit,
    private val contractCallback:(String)->Unit
) :BottomSheetDialog(nonNullContext) {


    private val binding:LayoutPermissionListBinding by lazy {
        LayoutPermissionListBinding.inflate(layoutInflater)
    }

    private val permissionRecyclerViewImplementor :PermissionRecyclerViewImplementor by lazy {
        PermissionRecyclerViewImplementor(intentCallback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(false)

        setContentView(binding.root)
        Log.d("bindViewLogger", "inside on create CALLED")
        initView()
        setClickListener()
    }

    private fun setClickListener() {
        binding.apply {
            ivClose.setOnClickListener {
                dismissDialog()
            }
        }
    }

    private fun initView() {
        binding.apply {
            permissionRecyclerViewImplementor.setData(permissionModal)
            rlPermission.adapter = GenericRecyclerView(permissionRecyclerViewImplementor)
        }

    }

    fun showDialog(){
        show()
    }

    private fun dismissDialog(){
        dismiss()
    }


    private fun setData(permissionModal: java.util.ArrayList<PermissionModal>) {
        permissionRecyclerViewImplementor.setData(permissionModal)
        binding.rlPermission.adapter?.notifyDataSetChanged()

    }

    /**
     *  called from the main activity on Resume whenever the user comes back to the app from the
     *  settings screen to allow the allow the permission.
     *
     */
    fun onCheckPermission(permissionModal:ArrayList<PermissionModal>, permissionView : LayoutPermissionViewBinding, callback:()->Unit){
        permissionModal.clear()
        permissionModal.addDefaultPermission(nonNullContext){permissionStatus->
            if(permissionStatus){
                dismissDialog()
                runOnMainThread {  permissionView.root.visibility(View.GONE) }
                callback.invoke()
                return@addDefaultPermission
            }
            runOnMainThread {
                setData(permissionModal)
                permissionView.root.visibility(View.VISIBLE)
            }

        }

    }
}