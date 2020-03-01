package com.mjob.bigburger.ui.cart.contract

import com.mjob.bigburger.repository.data.entities.CartItem

interface OnUpdatingCartItemListener {
    fun openUpdateCartItemDialog(cartItem: CartItem)

}