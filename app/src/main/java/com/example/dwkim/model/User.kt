package com.example.dwkim.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("nickname") val nickName: String? = null,
    @SerializedName("introduction") val introduction: String? = null,
    @SerializedName("pwd") val pwd: String? = null
)