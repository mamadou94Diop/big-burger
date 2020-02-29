package com.mjob.bigburger.repository.api.model

import com.google.gson.annotations.SerializedName

class Product(
    @SerializedName("ref")
    val reference: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,

    @SerializedName("price")
    val price: Int
)