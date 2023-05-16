package com.example.yieldmobile.data

import com.example.yieldmobile.data.dto.PaginatedTaskResourceCollectionResponse
import com.example.yieldmobile.domain.GetPrefixedApiBearerTokenUseCase
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskRetrofitApi: TaskRetrofitApi,
    private val apiBearerTokenUseCase: GetPrefixedApiBearerTokenUseCase,
) {
    suspend fun getPaginatedItems(pageNumber: Int): PaginatedTaskResourceCollectionResponse {
        return taskRetrofitApi.getPaginatedItems(apiBearerTokenUseCase(), pageNumber).body()!!
    }
}