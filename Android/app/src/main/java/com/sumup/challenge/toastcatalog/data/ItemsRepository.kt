package com.sumup.challenge.toastcatalog.data

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.sumup.challenge.toastcatalog.remote.NetworkClient
import com.sumup.challenge.toastcatalog.remote.NetworkClientImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

interface ItemsRepository {
    suspend fun getItems(): List<Item>
}

class ItemsRepositoryImpl(
    val remoteSource: NetworkClient = NetworkClientImpl()
): ItemsRepository {

    override suspend fun getItems(): List<Item> = withContext(Dispatchers.IO) {

        val response = remoteSource.getItems()

        try {
            val gson = GsonBuilder().create()
            val typeToken = object : TypeToken<List<Item>>() {}.type
            val responseList = gson.fromJson<List<Item>>(response, typeToken)

            //Lets transform the date to something human readable here
            //This will ensure we arent parsing dates every composition
            //We could also do the currency conversion here, for now it is in the composable
            //"2020-11-28T15:14:22Z"
            responseList.map {
                it.last_sold = Instant.parse(it.last_sold).atOffset(ZoneOffset.UTC).format(
                    DateTimeFormatter.ofPattern( "MM/dd/uu HH:mm a" ) )
            }
            return@withContext responseList
        } catch (e: Exception) {
            Log.e("Error", e.stackTraceToString())
            return@withContext emptyList()
        }
    }

}