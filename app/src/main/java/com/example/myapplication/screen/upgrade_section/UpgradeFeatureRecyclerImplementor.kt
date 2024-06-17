package com.example.myapplication.screen.upgrade_section

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.abstract_managers.BaseRecyclerView
import com.example.myapplication.abstract_managers.GenericViewHolder
import com.example.myapplication.databinding.ItemPermissionAskBinding

class UpgradeFeatureRecyclerImplementor() : BaseRecyclerView {
    private lateinit var featureModel: ArrayList<FeatureModel>
    override fun getItemCount(): Int {
        return featureModel.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        return GenericViewHolder(
            ItemPermissionAskBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        (holder.binding as ItemPermissionAskBinding).apply {
            featureModel.apply {
                ivPermissionIcon.setImageResource(this[position].featureIcon)
                tvPermissionName.text = this[position].featureName
                tvPermissionDesc.text = this[position].featureDesc
            }
        }
    }

    override fun <T : Any> setData(dataList: ArrayList<T>) {
        featureModel = ArrayList()
        featureModel.addAll(dataList.filterIsInstance<FeatureModel>())
    }

}