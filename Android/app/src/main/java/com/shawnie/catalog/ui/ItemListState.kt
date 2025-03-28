package com.shawnie.catalog.ui

import com.shawnie.catalog.ui.model.ItemUiModel

/**
 * Representative UI state for displaying list of toast items
 */
data class ItemListState (
    val itemList: List<ItemUiModel> = emptyList(),
    val loading: Boolean = true
)