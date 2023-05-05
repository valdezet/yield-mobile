package com.example.yieldmobile.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private const val PREFERENCE_NAME = "auth"
private val Context.apiAuthDataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

class ApiAuthPreferencesDataStore {
    companion object {
        @JvmStatic
        private val API_TOKEN_FIELD_KEY = stringPreferencesKey("api_token")

        @JvmStatic
        suspend fun storeToken(appContext: Context, apiToken: String) {
            appContext.apiAuthDataStore.edit { prefs ->
                prefs[API_TOKEN_FIELD_KEY] = apiToken
            }
        }

        suspend fun retrieveToken(appContext: Context): String? {
            return appContext.apiAuthDataStore.data.map { prefs -> prefs[API_TOKEN_FIELD_KEY] }.first()
        }
    }
}