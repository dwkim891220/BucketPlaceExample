package com.example.dwkim.result

import com.example.dwkim.model.Card
import com.example.dwkim.model.User
import com.google.gson.annotations.SerializedName

data class GetCardResult(
    @SerializedName("card") val card: Card,
    @SerializedName("user") val user: User,
    @SerializedName("recommend_cards") val recommendCards: List<Card>
) : BaseResult()