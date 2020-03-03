package com.mjob.bigburger.injection.modules

import android.content.Context
import androidx.room.Room
import com.mjob.bigburger.BigBurgerApplication
import com.mjob.bigburger.BuildConfig
import com.mjob.bigburger.repository.api.ProductRepository
import com.mjob.bigburger.repository.api.implementation.ProductApiService
import com.mjob.bigburger.repository.api.implementation.RemoteProductRepository
import com.mjob.bigburger.repository.data.CartRepository
import com.mjob.bigburger.repository.data.Database
import com.mjob.bigburger.repository.data.dao.CartDao
import com.mjob.bigburger.repository.data.implementation.LocalCartRepository
import com.mjob.bigburger.utils.CoroutineContextProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideContext(application: BigBurgerApplication): Context = application

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): Database {
        val databaseName = "big_burger_database"
        return Room.databaseBuilder<Database>(
            context.applicationContext, Database::class.java,
            databaseName
        ).build()
    }

    @Provides
    fun provideCartDao(database: Database): CartDao {
        return database.cartDao()
    }

    @Singleton
    @Provides
    fun provideCoroutineContextProvider() = CoroutineContextProvider()


    @Singleton
    @Provides
    fun providePictureApiService(retrofit: Retrofit): ProductApiService {
        return retrofit.create(ProductApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteProductRepository(
        productApiService: ProductApiService
    ): ProductRepository {
        return RemoteProductRepository(productApiService)
    }

    @Singleton
    @Provides
    fun provideLocalCartRepository(
        cartDao: CartDao,
        coroutineContextProvider: CoroutineContextProvider
    ): CartRepository {
        return LocalCartRepository(cartDao, coroutineContextProvider)
    }


}

