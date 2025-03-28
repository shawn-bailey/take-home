package com.sumup.challenge.toastcatalog.data

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.sumup.challenge.toastcatalog.remote.NetworkClient
import com.sumup.challenge.toastcatalog.remote.NetworkClientImpl
import com.sumup.challenge.toastcatalog.ui.model.ItemUiModel
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
            val typeToken = object : TypeToken<List<Item>>() {}.type
            val responseList = gson.fromJson<List<Item>>(response, typeToken)
            val itemUiModelList: MutableList<ItemUiModel> = mutableListOf()

            //Lets transform the date to something human readable here
            //This will ensure we arent parsing dates every composition
            //We could also do the currency conversion here, for now it is in the composable
            //"2020-11-28T15:14:22Z"
            responseList.map {
                val dateString = Instant.parse(it.last_sold).atOffset(ZoneOffset.UTC).format(
                    DateTimeFormatter.ofPattern( "MM/dd/uu HH:mm a" ) )

                val priceString = when (it.currency) {
                    EURO -> it.price + "€"
                    else -> { "$" }
                }
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