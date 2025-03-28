package com.shawnie.catalog.presentation.model

/**
 * Data class representing gastronomically significant toast
 */
data class ItemUiModel(
    val name: String = "",
    val price: String = "",
    val id: Int = 0,
    val lastSold: String = ""
)