package com.example.myapplication.abstract_managers

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

interface BaseRecyclerView{
     fun getItemCount():Int
     fun onCreateViewHolder(parent: ViewGroup, viewType: Int):GenericViewHolder
     fun onBindViewHolder(holder: GenericViewHolder, position:Int)
     fun <T: Any> setData(dataList:ArrayList<T>)
}