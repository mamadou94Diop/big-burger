package com.mjob.bigburger.repository.data

import com.mjob.bigburger.repository.api.model.Product
import com.mjob.bigburger.repository.data.dao.CartDao
import com.mjob.bigburger.repository.data.entities.CartItem
import com.mjob.bigburger.repository.data.implementation.LocalCartRepository
import helpers.TestCoroutineContextProvider
import helpers.TestCoroutinesRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class CartRepositoryTest {

    @Mock
    lateinit var mockDao: CartDao

    private lateinit var testCoroutineContextProvider: TestCoroutineContextProvider

    private lateinit var cartRepository: CartRepository


    @get:Rule
    val testCoroutinesRule = TestCoroutinesRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testCoroutineContextProvider = TestCoroutineContextProvider()
        cartRepository = LocalCartRepository(mockDao, testCoroutineContextProvider)
    }

    @Test
    fun given_a_cart_item_when_this_cart_item_exists_already_in_database_then_update_it() {
        testCoroutinesRule.runBlocking {
            val product = Product("1","Apple Pie",
                "Very delicious pie","https://www.picture.com/apple_pie.png", 20)
            val cartItem = CartItem("1","Apple Pie",20,1)
            Mockito.`when`(mockDao.findCartItemByReference(Mockito.anyString())).thenReturn(cartItem)

            cartRepository.upsert(product, 2)

            Mockito.verify(mockDao).updateCartItem(cartItem)
            Mockito.verify(mockDao, Mockito.times(0)).insertCartItem(cartItem)
        }

    }

    @Test
    fun given_a_cart_item_when_this_cart_item_does_not_exist_in_database_then_insert_it() {
        testCoroutinesRule.runBlocking {
             val product = Product("1","Apple Pie",
            "Very delicious pie","https://www.picture.com/apple_pie.png", 20)

            val cartItem = CartItem("1","Apple Pie",20,1)

            Mockito.`when`(mockDao.findCartItemByReference(Mockito.anyString())).thenReturn(null)

            cartRepository.upsert(product, 1)

            Mockito.verify(mockDao, Mockito.times(0)).updateCartItem(cartItem)
            Mockito.verify(mockDao).insertCartItem(cartItem)
      }
    }
}