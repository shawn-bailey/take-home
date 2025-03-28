package com.sumup.challenge.toastcatalog

import android.util.Log
import com.sumup.challenge.toastcatalog.data.Item
import com.sumup.challenge.toastcatalog.data.ItemsRepository
import com.sumup.challenge.toastcatalog.data.ItemsRepositoryImpl
import com.sumup.challenge.toastcatalog.remote.NetworkClient
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class ItemsRepositoryTest {

    //Mocked dependency
    val mockNetworkClient: NetworkClient = mockk(relaxed = true)

    //Real object under test
    val itemsRepository: ItemsRepository = ItemsRepositoryImpl(mockNetworkClient)

    @Test
    fun testExpectedResponse() = runBlocking {
        coEvery { mockNetworkClient.getItems() } returns EXPECTED_RESPONSE_DATA
        val result = itemsRepository.getItems()
        Assert.assertEquals(3, result.size)
    }

    @Test
    fun testEmptyResponse() = runBlocking {
        mockkStatic(Log::class)
        every { Log.e(any(), any()) } returns 0
        coEvery { mockNetworkClient.getItems() } returns ""
        val result = itemsRepository.getItems()
        Assert.assertEquals(emptyList<Item>(), result)
    }

    @Test
    fun testUnexpectedResponse() = runBlocking {
        mockkStatic(Log::class)
        every { Log.e(any(), any()) } returns 0
        coEvery { mockNetworkClient.getItems() } returns UNEXPECTED_RESPONSE_DATA
        val result = itemsRepository.getItems()
        Assert.assertEquals(emptyList<Item>(), result)
    }
}