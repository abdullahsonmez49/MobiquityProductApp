package com.mobiquity.productapp.views

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.mobiquity.productapp.MainCoroutineRule
import com.mobiquity.productapp.runBlockingTest
import com.mobiquity.productapp.utils.testProduct2
import com.mobiquity.productapp.viewmodel.ProductDetailViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

@HiltAndroidTest
class ProductDetailViewModelTest {

    private lateinit var viewModel: ProductDetailViewModel
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val coroutineRule = MainCoroutineRule()

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)
        .around(coroutineRule)

    @Before
    fun setUp() {
        hiltRule.inject()

        val savedStateHandle: SavedStateHandle = SavedStateHandle().apply {
            set("product", testProduct2)
        }
        viewModel = ProductDetailViewModel(savedStateHandle)
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    @Test
    @Throws(InterruptedException::class)
    fun testDefaultValues() = coroutineRule.runBlockingTest {
        Assert.assertEquals("Cola", viewModel.product.name)
    }
}