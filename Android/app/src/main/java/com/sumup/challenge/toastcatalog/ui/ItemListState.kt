package com.sumup.challenge.toastcatalog.ui

import com.sumup.challenge.toastcatalog.data.Item

/**
 * Representative UI state for displaying list of toast items
 */
data class ItemListState (
    val itemList: List<Item> = emptyList(),
    val loading: Boolean = true
)