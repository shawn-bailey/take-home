package com.shawnie.catalog.domain.data

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
    val last_sold: String = ""
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

fun ItemRemoteModel.dateString(): String = Instant.parse(last_sold)
    .atOffset(ZoneOffset.UTC)
    .format(DateTimeFormatter
        .ofPattern( "MM/dd/uu HH:mm a" )
    )
