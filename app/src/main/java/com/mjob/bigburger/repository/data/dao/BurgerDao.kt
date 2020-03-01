package com.mjob.bigburger.repository.data.dao

interface BurgerDao {
    fun toggleFavourite(burgerId : String, isFavourite : Boolean)

}