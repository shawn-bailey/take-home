package com.shawnie.catalog.domain.remote

import okhttp3.OkHttpClient
import okhttp3.Request

const val ENDPOINT = "https://my-json-server.typicode.com/sumup-challenges/mobile-coding-challenge-data/items/"

interface NetworkClient {
    suspend fun getItems(): String
}

class NetworkClientImpl(
    val client: OkHttpClient = OkHttpClient()
): NetworkClient {

    override suspend fun getItems(): String {
        val request = Request.Builder().url(ENDPOINT).build()
        val response = client.newCall(request).execute()
        return response.body?.string() ?: ""
    }

}
