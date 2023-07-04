package com.example.mvvm_with_clean.data.data

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)