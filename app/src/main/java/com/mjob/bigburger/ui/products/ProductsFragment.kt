package com.mjob.bigburger.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mjob.bigburger.R
import com.mjob.bigburger.injection.viewmodel.ViewModelFactory
import com.mjob.bigburger.repository.api.model.Product
import com.mjob.bigburger.ui.products.adapter.ProductsAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_products.*
import javax.inject.Inject

class ProductsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var productsViewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productsViewModel =
            ViewModelProvider(this, viewModelFactory).get(ProductsViewModel::class.java)

        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        productsViewModel.productsLiveData.observe(viewLifecycleOwner, Observer { products ->
            hideLoadingMessage()
            products?.let {
                showDataFetched(products);
            } ?: run {
                showErrorMessage()
            }
        })

    }

      private fun initListeners() {
          retry.setOnClickListener {
            hideErrorMessage()
            showLoadingMessage()
            productsViewModel.getProducts()
          }
    }

    private fun showLoadingMessage() {
        loading_data_message.playAnimation()
        loading_data_message.visibility = VISIBLE

    }

    private fun hideLoadingMessage() {
        loading_data_message.cancelAnimation()
        loading_data_message.visibility = INVISIBLE

    }

    private fun hideErrorMessage() {
        error_data_message.cancelAnimation()
        error_data_message.visibility = INVISIBLE
        retry.visibility = INVISIBLE
    }
    private fun showErrorMessage() {
        loading_data_message.playAnimation()
        error_data_message.visibility = VISIBLE
        retry.visibility = VISIBLE
    }

    private fun showDataFetched(products: List<Product>) {
        productsRecyclerView.visibility = VISIBLE
        productsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ProductsAdapter(products)
        }

    }
}
