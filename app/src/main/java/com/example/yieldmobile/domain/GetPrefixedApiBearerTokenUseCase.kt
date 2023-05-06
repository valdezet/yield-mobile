package com.example.yieldmobile.domain

import com.example.yieldmobile.data.AuthRepository
import javax.inject.Inject

class GetPrefixedApiBearerTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): String? {
        val token = authRepository.retrieveLocallyStoredApiToken() ?: return null
        return "Bearer $token"
    }
}