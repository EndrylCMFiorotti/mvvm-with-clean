package com.example.mvvm_with_clean.domain.user

import com.google.gson.annotations.SerializedName

data class Pets(
    @SerializedName("name")
    val name: String,
    @SerializedName("breed")
    val breed: String
)