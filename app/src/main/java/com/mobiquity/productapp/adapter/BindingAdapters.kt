package com.mobiquity.productapp.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mobiquity.productapp.R

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, url: String) {

    Glide.with(view.context)
        .load("http://mobcategories.s3-website-eu-west-1.amazonaws.com$url")
        .placeholder(R.drawable.brokenimage_foreground)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)

}
