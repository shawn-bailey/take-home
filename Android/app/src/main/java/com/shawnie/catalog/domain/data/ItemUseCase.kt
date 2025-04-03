package com.shawnie.catalog.domain.data

import com.shawnie.catalog.common.Resource
import com.shawnie.catalog.presentation.model.ItemUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ItemUseCase (val repository: ItemsRepository){

    operator fun invoke(): Flow<Resource<List<ItemUiModel>>> = flow {
        try {
            emit(Resource.Loading())
            val items = repository.getItems().map {
                it.toItemUiModel()
            }
            emit(Resource.Success(items))
        } catch (e: Exception) {
            emit(Resource.Error("An error occured"))
        }
    }
}