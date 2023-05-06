package com.example.yieldmobile.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val PREFERENCE_NAME = "auth"
private val Context.apiAuthDataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

class ApiAuthPreferencesDataStore @Inject constructor(
    @ApplicationContext private val appContext: Context
) {
    private val API_TOKEN_FIELD_KEY = stringPreferencesKey("api_token")

    suspend fun storeToken(apiToken: String) {
        appContext.apiAuthDataStore.edit { prefs ->
            prefs[API_TOKEN_FIELD_KEY] = apiToken
        }
    }

    suspend fun retrieveToken(): String? {
        return appContext.apiAuthDataStore.data.map { prefs -> prefs[API_TOKEN_FIELD_KEY] }.first()
    }
}