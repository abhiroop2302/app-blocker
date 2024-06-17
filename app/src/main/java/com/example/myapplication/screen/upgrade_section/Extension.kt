package com.example.myapplication.screen.upgrade_section

import android.content.Context
import com.example.myapplication.R

fun Context.fetchUpgradeFeatureList( callback:(ArrayList<FeatureModel>)->Unit){
    val featureNameList = resources.getStringArray(R.array.upgrade_feature_name)
    val featureDescList = resources.getStringArray(R.array.upgrade_feature_desc)
    val featureResponse = ArrayList<FeatureModel>()

    for(feature in featureNameList.indices)
        featureResponse.add(
            FeatureModel(
                R.drawable.ic_nav_lock_selected,
                featureNameList[feature],
                featureDescList[feature]
            )
        )

    callback(featureResponse)
}