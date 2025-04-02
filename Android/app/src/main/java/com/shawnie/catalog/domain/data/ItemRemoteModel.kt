package com.shawnie.catalog.domain.data

import com.google.gson.annotations.SerializedName
import com.shawnie.catalog.common.CURRENCY_MAP
import com.shawnie.catalog.presentation.model.ItemUiModel
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 * Data class representing gastronomically significant toast
 */
data class ItemRemoteModel(
    val name: String = "",
    val price: String = "",
    val id: Int = 0,
    val currency: String = "",
    @SerializedName("last_sold")
    val lastSold: String = ""
)

fun ItemRemoteModel.toItemUiModel(): ItemUiModel {
    return ItemUiModel(
        name = name,
        price = priceString(),
        id = id,
        lastSold = dateString()
    )
}

fun ItemRemoteModel.priceString() = price + CURRENCY_MAP[currency]

//Lets transform the date to something human readable here
//This will ensure we arent parsing dates every composition
//We could also do the currency conversion here, for now it is in the composable
//"2020-11-28T15:14:22Z"
fun ItemRemoteModel.dateString(): String = Instant.parse(lastSold)
    .atOffset(ZoneOffset.UTC)
    .format(DateTimeFormatter
        .ofPattern( "MM/dd/uu HH:mm a" )
    )
