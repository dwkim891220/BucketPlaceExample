package com.example.dwkim.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.dwkim.R
import com.example.dwkim.model.CardModel
import com.example.dwkim.repository.model.Card
import com.example.dwkim.view.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.item_card.view.*

class CardListAdapter(private val glide: RequestManager) : BaseRecyclerViewAdapter<CardModel>() {
    lateinit var onClick: (Int?) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CardViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? CardViewHolder)?.run {
            onBind(glide, getItem(position), onClick)
        }
    }

    inner class CardViewHolder(view: View): BaseViewHolder(view){
        fun onBind(glide: RequestManager, item: CardModel?, click: (Int?) -> Unit){
            item?.run {
                glide.load(imgUrl).into(itemView.iv_iCard)

                itemView.setOnClickListener {
                    click(item.id)
                }
            }
        }
    }
}