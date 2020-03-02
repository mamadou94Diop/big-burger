package com.mjob.bigburger.ui.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mjob.bigburger.R
import com.mjob.bigburger.repository.data.entities.CartItem
import com.mjob.bigburger.ui.cart.contract.CartItemEventListener
import javax.inject.Inject

class CartItemAdapter @Inject constructor(var cartItems: List<CartItem>, private val eventListener: CartItemEventListener) :
    RecyclerView.Adapter<CartItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return CartItemViewHolder(view, eventListener)
    }

    override fun getItemCount(): Int {
        return cartItems.count()
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.bindTo(cartItem)
    }
}