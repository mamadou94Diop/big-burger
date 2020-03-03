package com.mjob.bigburger.repository.data.implementation

import androidx.lifecycle.LiveData
import com.mjob.bigburger.repository.api.model.Product
import com.mjob.bigburger.repository.data.CartRepository
import com.mjob.bigburger.repository.data.dao.CartDao
import com.mjob.bigburger.repository.data.entities.CartItem
import com.mjob.bigburger.utils.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocalCartRepository @Inject constructor(private val dao: CartDao , private val coroutineContextProvider: CoroutineContextProvider) : CartRepository {
    override suspend fun upsert(product: Product, quantity: Int) {
        CoroutineScope(coroutineContextProvider.io).launch {
            var cartItem = dao.findCartItemByReference(product.reference)
            cartItem?.let { it ->
                it.quantity += quantity
                dao.updateCartItem(it)
            } ?: run {
                with(product) {
                    cartItem = CartItem(reference, name, price, quantity)
                    dao.insertCartItem(cartItem!!)
                }
            }
        }

    }

    override  fun get(): LiveData<List<CartItem>?> = dao.findCartItems()

    override suspend fun update(cartItem: CartItem) {
        CoroutineScope(coroutineContextProvider.io).launch {
            dao.updateCartItem(cartItem)
        }
    }

    override suspend fun delete(cartItem: CartItem) {
        CoroutineScope(coroutineContextProvider.io).launch {
            dao.deleteCartItem(cartItem)
        }
    }

}

