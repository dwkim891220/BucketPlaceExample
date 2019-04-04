package com.example.dwkim.repository.result

import com.example.dwkim.repository.model.Card
import com.example.dwkim.repository.model.User
import com.google.gson.annotations.SerializedName

data class GetHomeResult(
    @SerializedName("popular_cards") val popularCards: List<Card>? = null,
    @SerializedName("popular_users") val popularUsers: List<User>? = null
) : BaseResult()