package com.shawnie.catalog.domain.data

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.shawnie.catalog.common.CURRENCY_MAP
import com.shawnie.catalog.common.EURO
import com.shawnie.catalog.domain.remote.NetworkClient
import com.shawnie.catalog.domain.remote.NetworkClientImpl
import com.shawnie.catalog.presentation.model.ItemUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

interface ItemsRepository {
    suspend fun getItems(): List<ItemUiModel>
}

class ItemsRepositoryImpl(
    val remoteSource: NetworkClient = NetworkClientImpl()
): ItemsRepository {

    override suspend fun getItems(): List<ItemUiModel> = withContext(Dispatchers.IO) {

        val response = remoteSource.getItems()

        try {
            val gson = GsonBuilder().create()
            val typeToken = object : TypeToken<List<ItemRemoteModel>>() {}.type
            val responseList = gson.fromJson<List<ItemRemoteModel>>(response, typeToken)
            val itemUiModelList: MutableList<ItemUiModel> = mutableListOf()

            //Lets transform the date to something human readable here
            //This will ensure we arent parsing dates every composition
            //We could also do the currency conversion here, for now it is in the composable
            //"2020-11-28T15:14:22Z"
            responseList.map {
                val dateString = Instant.parse(it.last_sold).atOffset(ZoneOffset.UTC).format(
                    DateTimeFormatter.ofPattern( "MM/dd/uu HH:mm a" ) )

                val priceString = it.price + CURRENCY_MAP[it.currency]

                itemUiModelList.add(
                    ItemUiModel(
                        name = it.name,
                        price = priceString,
                        id = it.id,
                        lastSold = dateString
                    )
                )
            }
            return@withContext itemUiModelList
        } catch (e: Exception) {
            Log.e("Error", e.stackTraceToString())
            return@withContext emptyList()
        }
    }

}