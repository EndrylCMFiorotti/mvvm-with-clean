package com.example.mvvm_with_clean.data.response

import com.example.mvvm_with_clean.domain.presentation.UserPresentation
import com.google.gson.annotations.SerializedName

data class UserResponse(
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
) {
    fun toUserPresentation() : UserPresentation = UserPresentation(
        name = name,
        email = email,
        age = age.toString()
    )
}