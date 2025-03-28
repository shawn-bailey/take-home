package com.shawnie.catalog.presentation

import com.shawnie.catalog.presentation.model.ItemUiModel

/**
 * Representative UI state for displaying list of toast items
 */
data class ItemListState (
    val itemList: List<ItemUiModel> = emptyList(),
    val loading: Boolean = true
)