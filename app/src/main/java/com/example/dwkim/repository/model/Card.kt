package com.example.dwkim.repository.model

import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("user_id") val user_id: Int? = null,
    @SerializedName("img_url") val img_url: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("id") val id: Int? = null
)