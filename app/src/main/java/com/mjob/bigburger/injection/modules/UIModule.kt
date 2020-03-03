package com.mjob.bigburger.injection.modules

import com.mjob.bigburger.MainActivity
import com.mjob.bigburger.ui.cart.CartFragment
import com.mjob.bigburger.ui.products.ProductsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UIModule {
    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesProductsFragment(): ProductsFragment

    @ContributesAndroidInjector
    abstract fun contributesCartFragment(): CartFragment

}