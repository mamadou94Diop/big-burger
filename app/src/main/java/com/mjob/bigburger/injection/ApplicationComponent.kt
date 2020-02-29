package com.mjob.bigburger.injection

import com.mjob.bigburger.BigBurgerApplication
import com.mjob.bigburger.injection.modules.RepositoryModule
import com.mjob.bigburger.injection.modules.UIModule
import com.mjob.bigburger.injection.modules.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, AndroidSupportInjectionModule::class, UIModule::class, ViewModelModule::class])
interface ApplicationComponent : AndroidInjector<BigBurgerApplication> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<BigBurgerApplication>

}