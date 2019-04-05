package com.example.dwkim.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.dwkim.R
import com.example.dwkim.model.CardModel
import com.example.dwkim.view.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.item_card_large.view.*

class LargeCardListAdapter(private val glide: RequestManager) : BaseRecyclerViewAdapter<CardModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        LargeCardViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_large, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? LargeCardViewHolder)?.run {
            onBind(glide, getItem(position))
        }
    }

    inner class LargeCardViewHolder(view: View): BaseViewHolder(view){
        fun onBind(glide: RequestManager, item: CardModel?){
            item?.run {
                glide.load(imgUrl).into(itemView.iv_iCardLarge)
                itemView.tv_iCardLarge.text = description
            }
        }
    }
}