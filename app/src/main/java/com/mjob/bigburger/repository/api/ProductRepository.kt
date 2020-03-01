package com.mjob.bigburger.repository.api

import androidx.lifecycle.MutableLiveData
import com.mjob.bigburger.repository.api.model.Product

interface ProductRepository {
    fun getProducts(): MutableLiveData<List<Product>?>
}