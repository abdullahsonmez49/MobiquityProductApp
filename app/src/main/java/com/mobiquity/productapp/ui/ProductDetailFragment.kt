package com.mobiquity.productapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.mobiquity.productapp.R
import com.mobiquity.productapp.databinding.ProductDetailFragmentBinding
import com.mobiquity.productapp.viewmodel.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private val productDetailViewModel: ProductDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<ProductDetailFragmentBinding>(
            inflater,
            R.layout.product_detail_fragment,
            container,
            false
        ).apply {
            viewModel = productDetailViewModel
            lifecycleOwner = viewLifecycleOwner

            var isToolbarShown = false

            productDetailScrollview.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                    val shouldShowToolbar = scrollY > toolbar.height

                    if (isToolbarShown != shouldShowToolbar) {
                        isToolbarShown = shouldShowToolbar
                        appbar.isActivated = shouldShowToolbar
                        toolbarLayout.isTitleEnabled = shouldShowToolbar
                    }
                }
            )

            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }
        }
        return binding.root
    }
}