package com.example.yieldmobile.data

import com.example.yieldmobile.data.dto.LoginForm
import com.example.yieldmobile.data.dto.LoginSuccessData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthRetrofitApi {

    @POST("/api/api-token")
    suspend fun login(@Body loginForm: LoginForm): Response<LoginSuccessData>

    @GET("/api/api-token/check")
    suspend fun checkToken(@Header("Authorization") bearer: String): Response<Unit>
}