package com.mobiquity.productapp.views

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.mobiquity.productapp.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

@HiltAndroidTest
class ProductsFragmentTest {

    private val activityTestRule = ActivityTestRule(MainActivity::class.java)

    private val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)

    @Test
    fun testBreadIsVisible() {
        onView(ViewMatchers.withText("Bread"))
            .check(matches(ViewMatchers.isDisplayed()))
    }
}
