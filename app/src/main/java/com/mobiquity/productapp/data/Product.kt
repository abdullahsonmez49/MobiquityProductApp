package com.mobiquity.productapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: String,
    val categoryId: String,
    val name: String,
    val url: String,
    val description: String,
    val salePrice: SalePrice
) : Parcelable