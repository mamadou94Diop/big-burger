package com.mjob.bigburger.ui.products.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mjob.bigburger.R
import com.mjob.bigburger.repository.api.model.Product
import com.mjob.bigburger.utils.displayPriceWithCurrency
import com.mjob.bigburger.utils.loadImageFromUrl

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindTo(product: Product) {
        val name: TextView = itemView.findViewById(R.id.name)
        name.text = product.name

        val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
        thumbnail.loadImageFromUrl(product.thumbnailUrl)

        val description: TextView = itemView.findViewById(R.id.description)
        description.text = product.description

        val price: TextView = itemView.findViewById(R.id.price)
        price.displayPriceWithCurrency(product.price)
    }
}