package com.mobiquity.productapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.productapp.data.Section
import com.mobiquity.productapp.databinding.ItemSectionBinding

class SectionAdapter : ListAdapter<Section, RecyclerView.ViewHolder>(ListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SectionViewHolder(
            ItemSectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = getItem(position)
        (holder as SectionViewHolder).bind(product)
    }

    class SectionViewHolder(private val binding: ItemSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val viewPool = RecyclerView.RecycledViewPool()

        fun bind(item: Section) {
            binding.apply {

                binding.sectionTitleTextView.text = item.name
                val layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                layoutManager.initialPrefetchItemCount = 3

                binding.titledSectionRecycler.run {
                    this.setRecycledViewPool(viewPool)
                    this.layoutManager = layoutManager
                    val productAdapter = ProductAdapter()
                    productAdapter.submitList(item.products)
                    this.adapter = productAdapter

                }
                executePendingBindings()
            }
        }
    }
}

private class ListDiffCallback : DiffUtil.ItemCallback<Section>() {

    override fun areItemsTheSame(oldItem: Section, newItem: Section): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Section, newItem: Section): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.description == newItem.description &&
                oldItem.name == newItem.name
    }
}
