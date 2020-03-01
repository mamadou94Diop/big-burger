package com.mjob.bigburger.repository.data

import androidx.lifecycle.LiveData
import com.mjob.bigburger.repository.api.model.Product
import com.mjob.bigburger.repository.data.entities.CartItem

interface CartRepository {
    suspend fun upsert(product: Product, quantity: Int) : LiveData<Boolean>
    suspend fun getCartItems(): LiveData<List<CartItem>>
}