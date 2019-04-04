package com.example.dwkim.repository.result

import com.example.dwkim.repository.model.User
import com.google.gson.annotations.SerializedName

data class GetUserResult (
    @SerializedName("user") val user: User? = null
) : BaseResult()