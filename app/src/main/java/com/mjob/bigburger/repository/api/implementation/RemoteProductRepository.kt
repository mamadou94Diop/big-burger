package com.mjob.bigburger.repository.api.implementation

import androidx.lifecycle.MutableLiveData
import com.mjob.bigburger.repository.api.ProductRepository
import com.mjob.bigburger.repository.api.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class RemoteProductRepository @Inject constructor(private val productApiService: ProductApiService) :
    ProductRepository {
    override fun getProducts(): MutableLiveData<List<Product>?> {
        val products: MutableLiveData<List<Product>?> = MutableLiveData()

         productApiService.getProducts()
            .enqueue(object : Callback<List<Product>> {
                override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                    if (response.isSuccessful) {
                        if (response.isSuccessful){
                            products.postValue(response.body())
                        }
                    } else {
                        products.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    products.postValue(null)
                }
            })
          return products
    }
}