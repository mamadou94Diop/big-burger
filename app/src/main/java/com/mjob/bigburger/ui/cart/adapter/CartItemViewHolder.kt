package com.mjob.bigburger.ui.cart.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mjob.bigburger.R
import com.mjob.bigburger.repository.data.entities.CartItem
import com.mjob.bigburger.ui.cart.contract.CartItemEventListener
import com.mjob.bigburger.utils.displayPriceWithCurrency

class CartItemViewHolder(itemView: View, private val listener: CartItemEventListener) :
    RecyclerView.ViewHolder(itemView) {
    fun bindTo(cartItem: CartItem) {
        val name: TextView = itemView.findViewById(R.id.item_name_value)
        name.text = cartItem.name

        val quantity: TextView = itemView.findViewById(R.id.item_quantity_value)
        quantity.text = cartItem.quantity.toString()

        val price: TextView = itemView.findViewById(R.id.item_unit_price_value)
        price.displayPriceWithCurrency(cartItem.price)

        val subTotal: TextView = itemView.findViewById(R.id.sub_total_value)
        subTotal.displayPriceWithCurrency(cartItem.price* cartItem.quantity)


        val updateCartItem: ImageView = itemView.findViewById(R.id.update_cart_item)
        updateCartItem.setOnClickListener {
            listener.onUpdate(cartItem)
        }

        val deleteCartItem: ImageView = itemView.findViewById(R.id.delete_cart_item)
        deleteCartItem.setOnClickListener {
            listener.onDelete(cartItem)
        }
    }
}