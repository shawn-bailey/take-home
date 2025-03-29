package com.shawnie.catalog.domain.data

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
