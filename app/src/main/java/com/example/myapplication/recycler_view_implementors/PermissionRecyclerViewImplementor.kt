package com.example.myapplication.recycler_view_implementors

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.abstract_managers.BaseRecyclerView
import com.example.myapplication.abstract_managers.GenericViewHolder
import com.example.myapplication.databinding.ItemPermissionAskBinding
import com.example.myapplication.recycler_view_data_modals.PermissionModal

/**
 *  This class is responsible for the handling of the
 *   permission recycler view.
 */
class PermissionRecyclerViewImplementor(
    private val listenerCallback : (Intent)->Unit
) : BaseRecyclerView{

    private val permissionModal:ArrayList<PermissionModal> = ArrayList()

    override fun getItemCount(): Int {
        return permissionModal.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        return GenericViewHolder(
            ItemPermissionAskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        Log.d("bindViewLogger", "inside bind view holder")
        holder.apply {
            initView(this)
            setClickListener(this)
        }
    }

    private fun setClickListener(holder: GenericViewHolder) {
        (holder.binding as ItemPermissionAskBinding).apply {
            root.setOnClickListener {
                listenerCallback.invoke(
                    permissionModal[holder.adapterPosition].permissionIntent
                )
            }

            tvEnable.setOnClickListener {
                root.performClick()
            }
        }
    }

    private fun initView(holder: GenericViewHolder) {
        (holder.binding as ItemPermissionAskBinding).apply {
            tvPermissionName.text = permissionModal[holder.adapterPosition].permissionName
            tvPermissionDesc.text = permissionModal[holder.adapterPosition].permissionDesc
            ivPermissionIcon.setImageResource(permissionModal[holder.adapterPosition].permissionIcon)
        }
    }

    override fun <T : Any> setData(dataList: ArrayList<T>) {
        Log.d("insideSetDataFunction","set data called")
        permissionModal.clear()
        permissionModal.addAll(dataList.filterIsInstance<PermissionModal>())
    }
}