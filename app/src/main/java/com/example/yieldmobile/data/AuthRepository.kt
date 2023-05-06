package com.example.yieldmobile.data

import com.example.yieldmobile.data.dto.LoginForm
import com.example.yieldmobile.exceptions.retrofit2.ValidationException
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthRetrofitApi,
    private val authPreferencesDataStore: ApiAuthPreferencesDataStore
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

    suspend fun checkToken(bearerToken: String): Boolean {
        val response = api.checkToken(bearerToken)
        if(!response.isSuccessful) {
            if(response.code() == 401) return false
            throw HttpException(response)
        }
        return true
    }

    suspend fun storeApiTokenLocally(apiToken: String) {
        authPreferencesDataStore.storeToken(apiToken)
    }

    suspend fun retrieveLocallyStoredApiToken(): String? {
        return authPreferencesDataStore.retrieveToken()
    }
}