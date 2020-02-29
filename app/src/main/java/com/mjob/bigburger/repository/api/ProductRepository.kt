package com.mjob.bigburger.repository.api

import com.mjob.bigburger.repository.api.model.Product

interface ProductRepository {
    fun getProducts(): List<Product>
}