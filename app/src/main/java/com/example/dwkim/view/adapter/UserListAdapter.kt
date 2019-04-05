package com.example.dwkim.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dwkim.R
import com.example.dwkim.model.UserModel
import com.example.dwkim.view.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.item_user.view.*

class UserListAdapter : BaseRecyclerViewAdapter<UserModel>() {
    lateinit var onClick: (Int?) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? UserViewHolder)?.run {
            onBind(getItem(position), onClick)
        }
    }

    inner class UserViewHolder(view: View): BaseViewHolder(view){
        fun onBind(item: UserModel?, click: (Int?) -> Unit){
            item?.run {
                itemView.tv_iUser_nickName.text = nickName
                itemView.tv_iUser_introduction.text = introduction
                itemView.setOnClickListener {
                    click(id)
                }
            }
        }
    }
}