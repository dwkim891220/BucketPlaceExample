package com.example.dwkim.repository

import com.example.dwkim.repository.result.GetCardResult
import com.example.dwkim.repository.result.GetCardsResult
import com.example.dwkim.repository.result.GetHomeResult
import com.example.dwkim.repository.result.GetUserResult
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface NetworkRepository {
    @FormUrlEncoded
    @POST(USERS)
    fun join(
        @Field("nickname") nickName: String,
        @Field("introduction") introduction: String,
        @Field("pwd") pwd: String
    ): Completable

    @FormUrlEncoded
    @POST(SIGN_IN)
    fun signIn(
        @Field("nickname") nickName: String,
        @Field("pwd") pwd: String
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