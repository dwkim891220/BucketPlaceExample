package com.example.dwkim.repository.result

import com.google.gson.annotations.SerializedName

abstract class BaseResult {
    @SerializedName("ok") var ok: Boolean = false
    @SerializedName("msg") var msg: String? = null
}