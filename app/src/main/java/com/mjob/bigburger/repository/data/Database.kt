package com.mjob.bigburger.repository.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mjob.bigburger.repository.data.dao.CartDao
import com.mjob.bigburger.repository.data.entities.CartItem

@Database(entities = [CartItem::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun cartDao(): CartDao
}