package com.mjob.bigburger.repository.api.implementation

import com.mjob.bigburger.repository.api.ProductRepository
import com.mjob.bigburger.repository.api.model.Product
import javax.inject.Inject

class RemoteProductRepository @Inject constructor(val productApiService: ProductApiService) : ProductRepository{
    override fun getProducts(): List<Product> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}