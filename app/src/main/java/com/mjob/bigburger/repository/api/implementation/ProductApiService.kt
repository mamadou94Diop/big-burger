package com.mjob.bigburger.repository.api.implementation

import com.mjob.bigburger.repository.api.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductApiService {
    @GET("/catalog")
    fun getProducts(): Call<List<Product>>
}