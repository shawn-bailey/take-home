package com.shawnie.catalog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shawnie.catalog.domain.data.ItemUseCase
import com.shawnie.catalog.domain.data.ItemsRepository
import com.shawnie.catalog.domain.data.ItemsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * View model that transfers toast items state to the compose view
 */
class ItemListViewModel(
    val itemUseCase: ItemUseCase = ItemUseCase()
): ViewModel() {

    val stateFlow: MutableStateFlow<ItemListState> = MutableStateFlow(ItemListState())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val items = itemsRepository.getItems()
            stateFlow.update { currentState ->
                currentState.copy(
                    itemList = items,
                    loading = false
                )
            }
        }
    }
}