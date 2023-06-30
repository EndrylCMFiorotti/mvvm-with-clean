package com.example.mvvm_with_clean.domain.user

data class UserDto(
    val name: String,
    val email: String,
    val password: String,
    val age: Int
)