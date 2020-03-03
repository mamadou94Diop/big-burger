package com.mjob.bigburger.repository.api.implementation

import androidx.lifecycle.MutableLiveData
import com.mjob.bigburger.repository.api.ProductRepository
import com.mjob.bigburger.repository.api.model.Product
import com.mjob.bigburger.repository.common.Resource
import com.mjob.bigburger.repository.common.Resource.Companion.STATUS_ERROR
import com.mjob.bigburger.repository.common.Resource.Companion.STATUS_LOADING
import com.mjob.bigburger.repository.common.Resource.Companion.STATUS_SUCCESS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class RemoteProductRepository @Inject constructor(private val productApiService: ProductApiService) :
    ProductRepository {
    override fun get(): MutableLiveData<Resource<List<Product>?>> {
        val products: MutableLiveData<Resource<List<Product>?>> = MutableLiveData()
        products.postValue(Resource(STATUS_LOADING, null, null))

        productApiService.getProducts()
            .enqueue(object : Callback<List<Product>> {
                override fun onResponse(
                    call: Call<List<Product>>,
                    response: Response<List<Product>>
                ) {
                    if (response.isSuccessful) {
                        if (response.isSuccessful) {
                            products.postValue(Resource(STATUS_SUCCESS, response.body(), null))
                        }
                    } else {
                        products.postValue(Resource(STATUS_ERROR, null, null))
                    }
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    products.postValue(Resource(STATUS_ERROR, null, null))
                }
            })
        return products
    }
}