package com.shawnie.catalog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shawnie.catalog.common.Resource
import com.shawnie.catalog.domain.data.ItemUseCase
import com.shawnie.catalog.domain.data.ItemsRepository
import com.shawnie.catalog.domain.data.ItemsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model that transfers toast items state to the compose view
 */
class ItemListViewModel @Inject constructor (
    val itemUseCase: ItemUseCase
): ViewModel() {

    val stateFlow: MutableStateFlow<ItemListState> = MutableStateFlow(ItemListState())

    init {
         getItems()
    }

    private fun getItems() {
        itemUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    stateFlow.update { currentState ->
                        currentState.copy(
                            itemList = result.data ?: emptyList(),
                            loading = false
                        )
                    }
                }
                is Resource.Error<*> ->  {
                    stateFlow.update { currentState ->
                        currentState.copy(
                            loading = false
                        )
                    }
                }
                is Resource.Loading<*> -> {
                    stateFlow.update { currentState ->
                        currentState.copy(
                            loading = true
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}