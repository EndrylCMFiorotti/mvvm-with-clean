package com.example.mvvm_with_clean.data.network.exception

import java.lang.Exception

data class ServerException(
    override val message: String?,
    val code: Int
) : Exception(message)