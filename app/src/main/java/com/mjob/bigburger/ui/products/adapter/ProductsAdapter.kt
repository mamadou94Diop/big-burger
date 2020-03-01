package com.mjob.bigburger.ui.products.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mjob.bigburger.R
import com.mjob.bigburger.repository.api.model.Product
import javax.inject.Inject

class ProductsAdapter @Inject constructor(val products: List<Product>) :
    RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.count()
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val picture = products[position]
        holder.bindTo(picture)
        holder.itemView.setOnClickListener {
        }
    }
}