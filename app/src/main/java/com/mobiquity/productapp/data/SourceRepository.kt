package com.mobiquity.productapp.data

import com.mobiquity.productapp.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SourceRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getSections() = apiService.getSections()
}