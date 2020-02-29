package com.mjob.bigburger.repository.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartItem (
    @PrimaryKey
    @ColumnInfo(name = "reference")
    val reference: String,

    @ColumnInfo(name = "name")
    val name : String,

    @ColumnInfo(name = "quantity")
    val quantity:  Int
)