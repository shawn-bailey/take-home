package shawnie.catalog

import com.shawnie.catalog.data.ItemsRepository
import com.shawnie.catalog.presentation.ItemListViewModel
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ItemRemoteModelListViewModelTest {

    @Test
    fun testFetchItemsOnViewModelCreate() = runBlocking {
        val viewModel = ItemListViewModel(mockk<ItemsRepository>(relaxed = true))
        coVerify { viewModel.itemsRepository.getItems() }
    }
}