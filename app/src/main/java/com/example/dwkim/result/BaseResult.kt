package com.example.dwkim.result

import com.google.gson.annotations.SerializedName

abstract class BaseResult {
    @SerializedName("ok") val ok: Boolean? = null
    @SerializedName("msg") val msg: String? = null
}