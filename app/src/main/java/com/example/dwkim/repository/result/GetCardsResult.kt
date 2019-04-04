package com.example.dwkim.repository.result

import com.example.dwkim.repository.model.Card
import com.google.gson.annotations.SerializedName

data class GetCardsResult(
    @SerializedName("cards") val cards: List<Card>
) : BaseResult()