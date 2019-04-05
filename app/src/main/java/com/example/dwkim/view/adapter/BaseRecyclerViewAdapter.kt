package com.example.dwkim.view.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val dataList: ArrayList<T?> = ArrayList()

    fun add(item: T){
        dataList.add(item)
        notifyItemInserted(dataList.size)
    }

    fun addAll(itemList: List<T>) {
        val prevSize = dataList.size
        dataList.addAll(itemList)
        notifyItemRangeInserted(prevSize, itemList.size)
    }

    fun clear() {
        dataList.clear()
        notifyDataSetChanged()
    }

    fun changeAll(itemList: List<T>){
        dataList.run{
            clear()
            addAll(itemList)
        }
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T? = dataList[position]

    override fun getItemCount(): Int = dataList.size
}