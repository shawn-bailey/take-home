package com.sumup.challenge.toastcatalog

import com.sumup.challenge.toastcatalog.data.ItemsRepository
import com.sumup.challenge.toastcatalog.ui.compose.ItemListViewModel
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