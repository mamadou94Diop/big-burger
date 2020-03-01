package com.mjob.bigburger.repository.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favouriteItem")
data class FavouriteItem (
    @PrimaryKey
    @ColumnInfo(name = "reference")
    val reference: String,

    @ColumnInfo(name = "name")
    val name : String,

    @ColumnInfo(name = "description")
    val description:  String,

    @ColumnInfo(name = "thumbnailPath")
    val thumbnailPath:  String
)