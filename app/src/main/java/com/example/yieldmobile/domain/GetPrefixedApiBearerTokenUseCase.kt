package com.example.yieldmobile.domain

import android.content.Context
import com.example.yieldmobile.data.ApiAuthPreferencesDataStore

class GetPrefixedApiBearerTokenUseCase {
    suspend operator fun invoke(appContext: Context): String? {
        val token = ApiAuthPreferencesDataStore.retrieveToken(appContext) ?: return null
        return "Bearer $token"
    }
}