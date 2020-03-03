package com.mjob.bigburger.injection.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mjob.bigburger.injection.viewmodel.ViewModelFactory
import com.mjob.bigburger.injection.viewmodel.ViewModelKey
import com.mjob.bigburger.ui.cart.CartViewModel
import com.mjob.bigburger.ui.products.ProductsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProductsViewModel::class)
    internal abstract fun bindProductsViewModel(productsViewModel: ProductsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    internal abstract fun bindCartViewModel(cartViewModel: CartViewModel): ViewModel
}