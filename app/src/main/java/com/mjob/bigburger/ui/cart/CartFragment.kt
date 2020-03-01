package com.mjob.bigburger.ui.cart

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
import com.mjob.bigburger.repository.data.entities.CartItem
import com.mjob.bigburger.ui.cart.adapter.CartItemAdapter
import com.mjob.bigburger.ui.cart.contract.OnUpdatingCartItemListener
import com.mjob.bigburger.utils.displayPriceWithCurrency
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_cart.*
import javax.inject.Inject

class CartFragment : DaggerFragment(), OnUpdatingCartItemListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var cartViewModel: CartViewModel

    private var cartItemAdapter : CartItemAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cartViewModel =
            ViewModelProvider(this, viewModelFactory).get(CartViewModel::class.java)
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
    }

    override fun openUpdateCartItemDialog(cartItem: CartItem) {
        val bottomSheetDialog = BottomSheetDialog(activity!!)
        val sheetView: View =
            activity!!.layoutInflater.inflate(R.layout.fragment_add_to_cart_bottom_sheet, null)

        val numberPicker =
            sheetView.findViewById<NumberPicker>(R.id.quantity)

        val updateCartItemButton =
            sheetView.findViewById<Button>(R.id.validate)

        numberPicker.minValue = 1
        numberPicker.maxValue = 100
        numberPicker.value = cartItem.quantity

        numberPicker.setOnValueChangedListener { _, _, newValue ->
            cartItem.quantity = newValue
        }

        updateCartItemButton.setOnClickListener {
            cartItem.quantity = numberPicker.value
            cartViewModel.updateCartItem(cartItem)
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(sheetView)

        bottomSheetDialog.show()
    }

    private fun updateUI()
    {
        cartViewModel.cartItemsLiveData.observe(viewLifecycleOwner, Observer { cartItems ->
            if (cartItems.isNullOrEmpty()) {
                showEmptyCart()
            } else {
                showDataFetched(cartItems)
                calculateTotal(cartItems)
            }
        })
    }
    private fun showEmptyCart() {
        layoutCartEmpty.visibility= VISIBLE
        layoutCartItems.visibility= INVISIBLE
    }

    private fun calculateTotal(cartItems: List<CartItem>) {
        val total = cartItems.sumBy { (it.price * it.quantity) }
        total_value.displayPriceWithCurrency(total)

    }

    private fun showDataFetched(cartItems: List<CartItem>) {
        layoutCartEmpty.visibility= INVISIBLE
        layoutCartItems.visibility= VISIBLE

        if (cartItemAdapter == null){
            cartItemAdapter = CartItemAdapter(cartItems, this@CartFragment)
            cartItemsRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = cartItemAdapter
            }
        } else{
          cartItemAdapter!!.cartItems = cartItems
            cartItemAdapter!!.notifyDataSetChanged()
        }
    }
}