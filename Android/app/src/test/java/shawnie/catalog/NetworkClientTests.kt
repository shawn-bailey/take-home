package shawnie.catalog

import com.shawnie.catalog.remote.NetworkClient
import com.shawnie.catalog.remote.NetworkClientImpl
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Test

const val EXPECTED_RESPONSE_DATA = """
[{
    "name": "Avocado Toast",
    "price": "5.99",
    "id": 1,
    "currency": "EUR",
    "last_sold": "2020-11-28T15:14:22Z"
  },
  {
    "name": "Bacon Toast",
    "id": 2,
    "price": "1.99",
    "currency": "EUR",
    "last_sold": "2021-01-30T02:24:04Z"
  },
  {
    "name": "Crunchy Toast",
    "id": 3,
    "price": "0.99",
    "currency": "EUR",
    "last_sold": "2021-03-17T03:45:47Z"
  }
]
"""

const val UNEXPECTED_RESPONSE_DATA = """
[{
    "name": "CABBAGES",
    "id": 3,
    "price": "0.99",
    "currency": "EUR",
    "last_sold": "2021-someDate-17T03:45:47Z"
  }
]
"""

class NetworkClientTests {

    //Mocked dependency
    val mockOkHttpClient: OkHttpClient = mockk(relaxed = true)

    //Real object under test
    val networkClient: NetworkClient = NetworkClientImpl(mockOkHttpClient)

    @Test
    fun testEndpointSuccess() = runBlocking {
        every { mockOkHttpClient.newCall(any()).execute() } returns Response.Builder()
            .code(200)
            .request(Request.Builder()
                .url("https://my-json-server.typicode.com/sumup-challenges/mobile-coding-challenge-data/items/")
                .build())
            .protocol(Protocol.HTTP_2)
            .message("")
            .body(
                ResponseBody.create(
            "application/json; charset=utf-8".toMediaType(), EXPECTED_RESPONSE_DATA
                ))
            .build()

        val result = networkClient.getItems()
        Assert.assertEquals(EXPECTED_RESPONSE_DATA, result)
    }

    @Test
    fun testEndpointFail() = runBlocking {
        every { mockOkHttpClient.newCall(any()).execute() } returns Response.Builder()
            .code(500)
            .request(Request.Builder()
                .url("https://my-json-server.typicode.com/sumup-challenges/mobile-coding-challenge-data/items/")
                .build())
            .protocol(Protocol.HTTP_2)
            .message("")
            .body(
                ResponseBody.create(
                    "application/json; charset=utf-8".toMediaType(), ""))
            .build()

        val result = networkClient.getItems()
        Assert.assertEquals("", result)
    }
}
