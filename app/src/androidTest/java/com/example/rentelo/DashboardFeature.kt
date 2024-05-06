package com.example.rentelo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.CoreMatchers.allOf
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DashboardFeature : BaseUITest() {
    @Test
    fun displayBachelorFriendlySection() {
        onView(
            allOf(
                withId(R.id.bachelor_friendly_label),
                isDescendantOfA(withId(R.id.layout_bachelor_friendly))
            )
        ).check(matches(withText("Bachelor Friendly"))).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.bachelor_friendly_icon),
                isDescendantOfA(withId(R.id.layout_bachelor_friendly))
            )
        ).check(matches(withDrawable(R.mipmap.bachelor_friendly))).check(matches(isDisplayed()))
    }
}