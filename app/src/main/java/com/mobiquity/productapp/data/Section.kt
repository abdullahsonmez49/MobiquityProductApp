package com.mobiquity.productapp.data

data class Section(
    val id: String,
    val name: String,
    val description: String,
    val products: List<Product>
)
