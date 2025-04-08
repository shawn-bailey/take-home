package com.shawnie.catalog.domain.data

import com.shawnie.catalog.common.Resource
import com.shawnie.catalog.presentation.model.ItemUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject


class ItemUseCase @Inject constructor (
    val repository: ItemsRepository
) {
    operator fun invoke(): Flow<Resource<List<ItemUiModel>>> = flow {
        try {
            emit(Resource.Loading())
            val items = repository.getItems().map {
                it.toItemUiModel()
            }
            emit(Resource.Success(items))
        } catch(ioe: IOException) {
            emit(Resource.Error("Unable to reach server :("))
        } catch (e: Exception) {
            emit(Resource.Error("An error :("))
        }
    }
}