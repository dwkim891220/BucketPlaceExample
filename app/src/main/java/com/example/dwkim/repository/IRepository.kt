package com.example.dwkim.repository

import com.example.dwkim.model.CardDetailModel
import com.example.dwkim.model.CardModel
import com.example.dwkim.model.HomeModel
import com.example.dwkim.model.UserModel
import com.example.dwkim.repository.model.User
import io.reactivex.Completable
import io.reactivex.Single

interface IRepository {
    fun join(nickname: String, introduction: String, pwd: String) : Completable
    fun signIn(nickname: String, pwd: String) : Completable
    fun getUser(id: Int): Single<UserModel>

    fun getHome(): Single<HomeModel>

    fun getCard(id: Int): Single<CardDetailModel>
    fun getCards(page: Int, per: Int): Single<List<CardModel>>
}