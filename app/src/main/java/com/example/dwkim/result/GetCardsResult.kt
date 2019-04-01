package com.example.dwkim.result

import com.example.dwkim.model.Card
import com.google.gson.annotations.SerializedName

data class GetCardsResult(
    @SerializedName("cards") val cards: List<Card>
) : BaseResult()