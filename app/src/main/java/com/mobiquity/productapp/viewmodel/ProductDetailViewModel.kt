package com.mobiquity.productapp.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mobiquity.productapp.data.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val product: Product = savedStateHandle.get<Product>("product")!!
}