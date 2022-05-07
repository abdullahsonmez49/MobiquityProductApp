package com.mobiquity.productapp.views

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.mobiquity.productapp.MainActivity
import com.mobiquity.productapp.R
import com.mobiquity.productapp.utils.testProduct1
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.assertion.ViewAssertions.matches
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.rules.RuleChain

@HiltAndroidTest
class ProductDetailFragmentTest {

    private val activityTestRule = ActivityTestRule(MainActivity::class.java)

    private val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)

    @Before
    fun jumpToProductDetailFragment() {
        activityTestRule.activity.apply {
            runOnUiThread {
                val bundle = Bundle().apply { putParcelable("product", testProduct1) }
                findNavController(R.id.nav_host).navigate(R.id.productDetailFragment, bundle)
            }
        }
    }

    @Test
    fun testProductNameIsVisible() {
        onView(withText(testProduct1.name)).check(matches(isDisplayed()));
    }

}