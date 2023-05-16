package com.example.yieldmobile.data

import com.example.yieldmobile.data.dto.PaginatedTaskResourceCollectionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface TaskRetrofitApi {
    @GET("/api/tasks")
    suspend fun getPaginatedItems(
        @Header("Authorization") bearer: String?,
        @Query("page") pageNumber: Int
    ): Response<PaginatedTaskResourceCollectionResponse>
}