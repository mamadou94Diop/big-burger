package com.mjob.bigburger.repository.api

import androidx.lifecycle.MutableLiveData
import com.mjob.bigburger.repository.api.model.Product
import com.mjob.bigburger.repository.common.Resource

interface ProductRepository {
    fun get(): MutableLiveData<Resource<List<Product>?>>
}