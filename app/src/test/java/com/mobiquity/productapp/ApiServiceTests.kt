package com.mobiquity.productapp

import com.mobiquity.productapp.api.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTests  : ApiServiceBaseTest(){

    private lateinit var service: ApiService

    @Before
    fun setup() {
        val url = mockWebServer.url("/")
        service = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Test
    fun api_service() {
        enqueue("api_response.json")
        runBlocking {
            val apiResponse = service.getSections()

            Assert.assertNotNull(apiResponse)

            apiResponse.body()?.let { Assert.assertTrue("The list was empty", it.isNotEmpty()) }

            Assert.assertEquals("The id's did not match", "36802",
                apiResponse.body()?.get(0)?.id ?: ""
            )
        }
    }
}