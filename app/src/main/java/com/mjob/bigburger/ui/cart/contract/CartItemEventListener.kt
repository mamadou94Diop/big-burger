package com.mjob.bigburger.ui.cart.contract

import com.mjob.bigburger.repository.data.entities.CartItem

interface CartItemEventListener {
    fun onUpdate(cartItem: CartItem)
    fun onDelete(cartItem: CartItem)
}