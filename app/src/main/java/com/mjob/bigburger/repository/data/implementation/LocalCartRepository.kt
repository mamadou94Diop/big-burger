package com.mjob.bigburger.repository.data.implementation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mjob.bigburger.repository.api.model.Product
import com.mjob.bigburger.repository.data.CartRepository
import com.mjob.bigburger.repository.data.dao.CartDao
import com.mjob.bigburger.repository.data.entities.CartItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocalCartRepository @Inject constructor(private val dao: CartDao) : CartRepository {
    override suspend fun upsert(product: Product, quantity: Int) {
        CoroutineScope(Dispatchers.IO).launch {
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
        CoroutineScope(Dispatchers.IO).launch {
            dao.updateCartItem(cartItem)
        }
    }

    override suspend fun delete(cartItem: CartItem) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteCartItem(cartItem)
        }
    }

}

