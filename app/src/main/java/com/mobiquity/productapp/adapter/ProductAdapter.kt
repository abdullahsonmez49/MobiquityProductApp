package com.mobiquity.productapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.productapp.data.Product
import com.mobiquity.productapp.databinding.ItemProductBinding
import com.mobiquity.productapp.ui.ProductsFragmentDirections

class ProductAdapter : ListAdapter<Product, RecyclerView.ViewHolder>(ListItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = getItem(position)
        (holder as ProductViewHolder).bind(product)
    }

    class ProductViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.product?.let { product ->
                    navigateToDetail(product, it)
                }
            }
        }

        private fun navigateToDetail(
            product: Product,
            view: View
        ) {
            val direction =
                ProductsFragmentDirections.actionProductsFragmentToProductDetailFragment(
                    product
                )
            view.findNavController().navigate(direction)
        }

        fun bind(item: Product) {
            binding.apply {
                product = item
                executePendingBindings()
            }
        }
    }
}

private class ListItemDiffCallback : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}
