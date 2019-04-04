package com.example.dwkim.model

import com.example.dwkim.repository.model.User

class UserModel(user: User) {
    val id: Int?
    val nickName: String?
    val introduction: String?
    val pwd: String?

    init {
        this.id = user.id
        this.nickName = user.nickname
        this.introduction = user.introduction
        this.pwd = user.pwd
    }
}