package com.mjob.bigburger.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mjob.bigburger.R

fun ImageView.loadImageFromUrl(
    url: String,
    onSuccessfulImageLoadingCallback: (() -> Unit)? = null,
    onFailedImageLoadingCallback: (() -> Unit)? = null
) {
    Glide.with(context)
        .load(url)
        .error(R.drawable.no_image)
        .addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onFailedImageLoadingCallback?.invoke()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onSuccessfulImageLoadingCallback?.invoke()
                return false
            }
        })
        .into(this)
}


fun TextView.displayPriceWithCurrency(price: Int){
    val priceInEuro : Double = price.toDouble()/100
    this.text = "$priceInEuro €"
}
