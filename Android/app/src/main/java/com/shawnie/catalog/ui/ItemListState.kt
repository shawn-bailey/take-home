package com.shawnie.catalog.ui

import com.shawnie.catalog.data.Item

/**
 * Representative UI state for displaying list of toast items
 */
data class ItemListState (
    val itemList: List<Item> = emptyList(),
    val loading: Boolean = true
)