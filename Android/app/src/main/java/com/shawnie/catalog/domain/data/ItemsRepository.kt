package com.shawnie.catalog.domain.data

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.shawnie.catalog.common.CURRENCY_MAP
import com.shawnie.catalog.domain.remote.NetworkClient
import com.shawnie.catalog.domain.remote.NetworkClientImpl
import com.shawnie.catalog.presentation.model.ItemUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

interface ItemsRepository {
    suspend fun getItems(): List<ItemRemoteModel>
}

class ItemsRepositoryImpl(
    val remoteSource: NetworkClient = NetworkClientImpl()
): ItemsRepository {

    override suspend fun getItems(): List<ItemRemoteModel> = withContext(Dispatchers.IO) {

        val response = remoteSource.getItems()

        try {
            val gson = GsonBuilder().create()
            val typeToken = object : TypeToken<List<ItemRemoteModel>>() {}.type
            val responseList = gson.fromJson<List<ItemRemoteModel>>(response, typeToken)
            val itemUiModelList: MutableList<ItemUiModel> = mutableListOf()

            responseList.map {
                itemUiModelList.add(it.toItemUiModel())
            }
            return@withContext responseList
        } catch (e: Exception) {
            Log.e("Error", e.stackTraceToString())
            return@withContext emptyList()
        }
    }

}