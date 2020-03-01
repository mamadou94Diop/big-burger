package com.mjob.bigburger.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mjob.bigburger.R
import com.mjob.bigburger.injection.viewmodel.ViewModelFactory
import com.mjob.bigburger.repository.api.model.Product
import com.mjob.bigburger.repository.common.Resource.Companion.STATUS_ERROR
import com.mjob.bigburger.repository.common.Resource.Companion.STATUS_LOADING
import com.mjob.bigburger.repository.common.Resource.Companion.STATUS_SUCCESS
import com.mjob.bigburger.ui.products.adapter.ProductsAdapter
import com.mjob.bigburger.ui.products.contract.OnAddingProductToCartListener
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_products.*
import javax.inject.Inject


class ProductsFragment : DaggerFragment(), OnAddingProductToCartListener {

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
        productsViewModel.productsLiveData.observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                STATUS_LOADING -> {
                    hideErrorMessage()
                    showLoadingMessage()
                }
                STATUS_ERROR -> {
                    hideLoadingMessage()
                    showErrorMessage()
                }
                STATUS_SUCCESS -> {
                    hideLoadingMessage()
                    showDataFetched(resource.data!!)
                }
            }
        })

    }

    override fun openAddProductToCartDialog(product: Product) {
        val bottomSheetDialog = BottomSheetDialog(activity!!)
        val sheetView: View =
            activity!!.layoutInflater.inflate(R.layout.fragment_add_to_cart_bottom_sheet, null)

        val numberPicker =
            sheetView.findViewById<NumberPicker>(R.id.quantity)

        val addToCartButton =
            sheetView.findViewById<Button>(R.id.validate)

        numberPicker.minValue = 1
        numberPicker.maxValue = 100

        addToCartButton.setOnClickListener {
            productsViewModel.addProductToCart(product, numberPicker.value)
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(sheetView)

        bottomSheetDialog.show()

    }

    private fun initListeners() {
        retry.setOnClickListener {
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
            adapter = ProductsAdapter(products, this@ProductsFragment)
        }
    }
}
