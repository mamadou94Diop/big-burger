package com.mjob.bigburger.repository.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mjob.bigburger.repository.api.implementation.ProductApiService
import com.mjob.bigburger.repository.api.implementation.RemoteProductRepository
import com.mjob.bigburger.repository.api.model.Product
import com.nhaarman.mockitokotlin2.any
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductRepositoryTest {
    @Mock
    lateinit var mockProductApiService: ProductApiService

    @Mock
    lateinit var mockProductCall: Call<List<Product>>

    @Mock
    lateinit var mockResponseBody: ResponseBody

    private lateinit var productRepository: ProductRepository

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        productRepository = RemoteProductRepository(mockProductApiService)
    }

    @Test
    fun given_a_webservice_when_invoking_it_successes_then_result_should_be_a_resource_with_data() {
        val product = Product("1","Apple Pie",
            "Very delicious pie","https://www.picture.com/apple_pie.png", 20)

            Mockito.`when`(mockProductApiService.getProducts()).thenReturn(mockProductCall)

        Mockito.doAnswer { invocation ->
            val callback: Callback<List<Product>> = invocation.getArgument(0)
            callback.onResponse(mockProductCall, Response.success(listOf(product)))

        }.`when`(mockProductCall).enqueue(Mockito.any())

        val result = productRepository.get().value
        assert(!result!!.isError())
        assert(result.data!![0].name == "Apple Pie")
        assert(result.data!![0].reference == "1")
        assert(result.data!![0].description == "Very delicious pie")
        assert(result.data!![0].thumbnailUrl == "https://www.picture.com/apple_pie.png")
        assert(result.data!![0].price == 20)


    }

    @Test
    fun given_a_webservice_when_invoking_it_does_not_then_result_should_be_a_resource_with_error_code_with_null_data() {
        Mockito.`when`(mockProductApiService.getProducts()).thenReturn(mockProductCall)

        Mockito.doAnswer { invocation ->
            val callback: Callback<List<Product>> = invocation.getArgument(0)
            callback.onResponse(mockProductCall, Response.error(500, mockResponseBody))

        }.`when`(mockProductCall).enqueue(any())

        val result = productRepository.get().value
        assert(result!!.isError())
        assert(result.data == null)
    }

    @Test
    fun given_a_cart_item_when_this_cart_item_does_not_exist_in_database_then_insert_it() {
        Mockito.`when`(mockProductApiService.getProducts()).thenReturn(mockProductCall)

        Mockito.doAnswer { invocation ->
            val callback: Callback<List<Product>> = invocation.getArgument(0)
            callback.onFailure(mockProductCall,  Exception("Network Error"))

        }.`when`(mockProductCall).enqueue(any())

        val result = productRepository.get().value
        assert(result!!.isError())
        assert(result.data == null)
    }


}