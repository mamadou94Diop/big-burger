package com.mjob.bigburger.repository.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mjob.bigburger.repository.data.entities.CartItem

@Dao
interface CartDao {
    @Insert
    fun insertCartItem(cartItem: CartItem)

    @Query("SELECT * FROM cart")
    fun findCartItems(): LiveData<List<CartItem>?>

    @Query("SELECT * FROM cart where reference=:ref")
    fun findCartItemByReference(ref: String): CartItem?

    @Delete
    fun deleteCartItem(cartItem: CartItem)

    @Update
    fun updateCartItem(cartItem: CartItem)

}