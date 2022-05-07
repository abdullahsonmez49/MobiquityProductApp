package com.mobiquity.productapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.productapp.adapter.SectionAdapter
import com.mobiquity.productapp.databinding.ProductsFragmentBinding
import com.mobiquity.productapp.util.Resource
import com.mobiquity.productapp.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "ProductsFragment"

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private val viewModel: ProductsViewModel by viewModels()
    private val concatAdapter = ConcatAdapter()
    private val sectionAdapter = SectionAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ProductsFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.progressBar.visibility = View.VISIBLE

        viewModel.responseLiveData.observe(viewLifecycleOwner) { sections ->
            when (sections) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    sectionAdapter.submitList(sections.data)
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    sections.message?.let { message ->
                        Log.e(TAG, "Error: $message")
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

        concatAdapter.addAdapter(sectionAdapter)
        val linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.sectionRecyclerview.run {
            layoutManager = linearLayoutManager
            adapter = concatAdapter
        }

        return binding.root
    }
}