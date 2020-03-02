package com.mjob.bigburger.ui.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjob.bigburger.repository.data.CartRepository
import com.mjob.bigburger.repository.data.entities.CartItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    var cartItemsLiveData: LiveData<List<CartItem>?> = MutableLiveData()

    init {
        getCartItems()
    }

    private fun getCartItems() {
        viewModelScope.launch {
            cartItemsLiveData = cartRepository.get()
        }
    }

    fun updateCartItem(cartItem: CartItem) {
        viewModelScope.launch {
            cartRepository.update(cartItem)
        }
    }

    fun deleteCartItem(cartItem: CartItem) {
        viewModelScope.launch {
            cartRepository.delete(cartItem)
        }
    }
}