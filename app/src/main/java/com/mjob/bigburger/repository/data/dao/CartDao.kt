package com.mjob.bigburger.repository.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.mjob.bigburger.repository.data.entities.CartItem

@Dao
interface CartDao {
    @Query("SELECT * FROM cart")
    fun getCartIms(): LiveData<CartItem>

    @Delete
    fun deleteCartItem(cartItem: CartItem)

    @Update
    fun updateCartItem(cartItem: CartItem)

}