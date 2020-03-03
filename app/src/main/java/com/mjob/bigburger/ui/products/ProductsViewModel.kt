package com.mjob.bigburger.ui.products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjob.bigburger.repository.api.ProductRepository
import com.mjob.bigburger.repository.api.model.Product
import com.mjob.bigburger.repository.common.Resource
import com.mjob.bigburger.repository.data.CartRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private  val cartRepository: CartRepository
) : ViewModel() {

    var productsLiveData: MutableLiveData<Resource<List<Product>?>> = MutableLiveData()

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            productsLiveData = productRepository.get()
        }
    }

    fun addProductToCart(product: Product, quantity: Int) {
        viewModelScope.launch {
            cartRepository.upsert(product, quantity)
        }
    }
}