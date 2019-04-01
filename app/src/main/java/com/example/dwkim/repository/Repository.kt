package com.example.dwkim.repository

import com.example.dwkim.model.User
import com.example.dwkim.result.GetCardResult
import com.example.dwkim.result.GetCardsResult
import com.example.dwkim.result.GetHomeResult
import com.example.dwkim.result.GetUserResult
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface Repository: IRepository {
    @POST(USERS)
    override fun join(
        @Body user: User
    ): Completable

    @POST(SIGN_IN)
    override fun signIn(
        @Body user: User
    ): Completable

    @GET("$USERS/{id}")
    override fun getUser(
        @Path(value = "id", encoded = true) id: Int
    ): Single<GetUserResult>

    @GET(HOME)
    override fun getHome(): Single<GetHomeResult>

    @GET("$CARDS/{id}")
    override fun getCard(
        @Path(value = "id", encoded = true) id: Int
    ): Single<GetCardResult>

    @GET(CARDS)
    override fun getCards(
        @Query("page") page: Int,
        @Query("per") per: Int
    ): Single<GetCardsResult>
}