package com.example.dwkim.repository

import com.example.dwkim.repository.model.User
import com.example.dwkim.repository.result.GetCardResult
import com.example.dwkim.repository.result.GetCardsResult
import com.example.dwkim.repository.result.GetHomeResult
import com.example.dwkim.repository.result.GetUserResult
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface NetworkRepository {
    @POST(USERS)
    fun join(
        @Body user: User
    ): Completable

    @POST(SIGN_IN)
    fun signIn(
        @Body user: User
    ): Completable

    @GET("$USERS/{id}")
    fun getUser(
        @Path(value = "id", encoded = true) id: Int
    ): Single<GetUserResult>

    @GET(HOME)
    fun getHome(): Single<GetHomeResult>

    @GET("$CARDS/{id}")
    fun getCard(
        @Path(value = "id", encoded = true) id: Int
    ): Single<GetCardResult>

    @GET(CARDS)
    fun getCards(
        @Query("page") page: Int,
        @Query("per") per: Int
    ): Single<GetCardsResult>
}