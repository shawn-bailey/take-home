package com.shawnie.catalog.data

/**
 * Data class representing gastronomically significant toast
 */
data class Item(
    val name: String = "",
    val price: String = "",
    val id: Int = 0,
    val currency: String = "",
    val last_sold: String = ""
)
