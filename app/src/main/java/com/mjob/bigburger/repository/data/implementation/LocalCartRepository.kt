package com.mjob.bigburger.repository.data.implementation

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mjob.bigburger.repository.api.model.Product
import com.mjob.bigburger.repository.data.CartRepository
import com.mjob.bigburger.repository.data.dao.CartDao
import com.mjob.bigburger.repository.data.entities.CartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class LocalCartRepository @Inject constructor(private val dao: CartDao) : CartRepository {
    override suspend fun upsert(product: Product, quantity: Int): LiveData<Boolean> {
        return liveData {
            var isUpsertSuccessful = true
            withContext(Dispatchers.IO) {
                try {
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
                } catch (exception : Exception){
                    isUpsertSuccessful = false
                }

            }
            emit(isUpsertSuccessful)
        }

    }

    override suspend fun getCartItems(): LiveData<List<CartItem>> {
        return liveData {
            var data: List<CartItem> = listOf()
            withContext(Dispatchers.IO) {
                data = dao.findCartItems()
            }
            emit(data)
        }
    }

}

