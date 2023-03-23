package com.chunglyric.taipeicityzootour.data

import retrofit2.Response

sealed class ResponseStatus<out R> {
    data class Success<out T>(val data: T) : ResponseStatus<T>()
    data class Error<T>(val response: Response<T>) : ResponseStatus<T>()
}

fun <T> Response<T>.parseResponse(): ResponseStatus<T> {
    val responseBody = this.body()
    return if (this.isSuccessful && responseBody != null) {
        ResponseStatus.Success(responseBody)
    } else {
        ResponseStatus.Error(this)
    }
}
