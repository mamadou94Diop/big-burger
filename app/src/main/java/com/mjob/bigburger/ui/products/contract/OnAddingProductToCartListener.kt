package com.mjob.bigburger.ui.products.contract

import com.mjob.bigburger.repository.api.model.Product

interface OnAddingProductToCartListener {
    fun openAddProductToCartDialog(product: Product)
}