package com.mjob.bigburger.ui.products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjob.bigburger.repository.api.ProductRepository
import com.mjob.bigburger.repository.api.model.Product
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    var productsLiveData: MutableLiveData<List<Product>?> = MutableLiveData()

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            productsLiveData = productRepository.getProducts()
        }
    }
}