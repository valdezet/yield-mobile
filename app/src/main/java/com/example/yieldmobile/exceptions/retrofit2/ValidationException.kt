package com.example.yieldmobile.exceptions.retrofit2

import com.example.yieldmobile.data.dto.ValidationErrorResponseBody
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.ResponseBody


class ValidationException(body: ResponseBody) : Exception() {

    val errorData: ValidationErrorResponseBody =
        ObjectMapper().readValue(body.string(), ValidationErrorResponseBody::class.java)

    fun <T> mapFieldErrors(clazz: Class<T>): T {
        return ObjectMapper().convertValue(errorData.errors, clazz)
    }
}