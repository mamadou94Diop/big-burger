package com.mjob.bigburger.ui.products.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mjob.bigburger.R
import com.mjob.bigburger.repository.api.model.Product
import com.mjob.bigburger.ui.products.contract.OnAddingProductToCartListener
import com.mjob.bigburger.utils.displayPriceWithCurrency
import com.mjob.bigburger.utils.loadImageFromUrl

class ProductViewHolder(itemView: View, private val listener: OnAddingProductToCartListener) :
    RecyclerView.ViewHolder(itemView) {
    fun bindTo(product: Product) {
        val name: TextView = itemView.findViewById(R.id.item_quantity_label)
        name.text = product.name

        val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
        thumbnail.loadImageFromUrl(product.thumbnailUrl)

        val addToCart: ImageView = itemView.findViewById(R.id.update_cart_item)
        addToCart.setOnClickListener {
            listener.openAddProductToCartDialog(product)
        }

        val description: TextView = itemView.findViewById(R.id.quantity)
        description.text = product.description

        val price: TextView = itemView.findViewById(R.id.unit_price)
        price.displayPriceWithCurrency(product.price)
    }
}