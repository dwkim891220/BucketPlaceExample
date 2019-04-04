package com.example.dwkim.repository

import com.example.dwkim.model.HomeModel
import com.example.dwkim.repository.model.Card
import com.example.dwkim.repository.model.User
import com.example.dwkim.repository.result.GetCardResult
import com.example.dwkim.repository.result.GetHomeResult
import io.reactivex.Completable
import io.reactivex.Single

interface IRepository {
    fun join(user: User) : Completable?
    fun signIn(user: User) : Completable?
    fun getUser(id: Int): Single<User>?

    fun getHome(): Single<HomeModel>?

    fun getCard(id: Int): Single<GetCardResult>?
    fun getCards(page: Int, per: Int): Single<List<Card>>?
}