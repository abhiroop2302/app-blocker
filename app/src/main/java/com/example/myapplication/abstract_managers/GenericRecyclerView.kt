package com.example.myapplication.abstract_managers

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 *  This is the generic recycler view class which has only one class and used for managing the
 *  all recycler view in the full app.
 */
class GenericRecyclerView(
    private val baseRecyclerView: BaseRecyclerView
):RecyclerView.Adapter<GenericViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        return baseRecyclerView.onCreateViewHolder(parent,viewType)
    }

    override fun getItemCount(): Int {
        return baseRecyclerView.getItemCount()
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        baseRecyclerView.onBindViewHolder(holder,position)
    }

}

class GenericViewHolder(val binding: ViewBinding):RecyclerView.ViewHolder(binding.root){

}