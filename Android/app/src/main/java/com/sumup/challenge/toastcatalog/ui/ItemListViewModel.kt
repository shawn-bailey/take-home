package com.sumup.challenge.toastcatalog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumup.challenge.toastcatalog.data.ItemsRepository
import com.sumup.challenge.toastcatalog.data.ItemsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * View model that transfers toast items state to the compose view
 */
class ItemListViewModel(
    val itemsRepository: ItemsRepository = ItemsRepositoryImpl()
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