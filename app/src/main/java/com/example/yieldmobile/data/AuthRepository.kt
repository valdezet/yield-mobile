package com.example.yieldmobile.data

import com.example.yieldmobile.data.dto.LoginForm
import com.example.yieldmobile.exceptions.retrofit2.ValidationException

class AuthRepository(
    private val api: AuthRetrofitApi
) {
    /**
     * @return String the Bearer token in plaintext
     */
    suspend fun login(loginForm: LoginForm): String {
        val response = api.login(loginForm)
        if (!response.isSuccessful) {
            if (response.code() == 422) {
                throw ValidationException(response.errorBody()!!)
            } else throw RuntimeException()
        }
        return response.body()!!.token
    }
}