package com.mjob.bigburger.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjob.bigburger.repository.api.model.Product
import com.mjob.bigburger.repository.data.CartRepository
import com.mjob.bigburger.repository.data.entities.CartItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    var cartItemsLiveData: LiveData<List<CartItem>> = MutableLiveData()

    init {
        getCartItems()
    }

    fun getCartItems() {
        viewModelScope.launch {
            cartItemsLiveData = cartRepository.getCartItems()
        }
    }

    fun addProductToCart(product: Product, quantity: Int) {
        viewModelScope.launch {
            cartRepository.upsert(product, quantity)
        }
    }
}