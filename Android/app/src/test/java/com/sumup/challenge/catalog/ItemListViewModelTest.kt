package com.sumup.challenge.catalog

import com.shawnie.catalog.data.ItemsRepository
import com.shawnie.catalog.ui.ItemListViewModel
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ItemListViewModelTest {

    @Test
    fun testFetchItemsOnViewModelCreate() = runBlocking {
        val viewModel = ItemListViewModel(mockk<ItemsRepository>(relaxed = true))
        coVerify { viewModel.itemsRepository.getItems() }
    }
}