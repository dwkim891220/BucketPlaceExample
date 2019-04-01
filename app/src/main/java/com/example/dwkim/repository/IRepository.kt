package com.example.dwkim.repository

import com.example.dwkim.model.User
import com.example.dwkim.result.GetCardResult
import com.example.dwkim.result.GetCardsResult
import com.example.dwkim.result.GetHomeResult
import com.example.dwkim.result.GetUserResult
import io.reactivex.Completable
import io.reactivex.Single

interface IRepository {
    fun join(user: User) : Completable
    fun signIn(user: User) : Completable
    fun getUser(id: Int): Single<GetUserResult>

    fun getHome(): Single<GetHomeResult>

    fun getCard(id: Int): Single<GetCardResult>
    fun getCards(page: Int, per: Int): Single<GetCardsResult>
}