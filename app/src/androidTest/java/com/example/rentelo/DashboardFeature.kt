package com.example.rentelo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
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

    @Test
    fun displayOwnersPropertySection() {
        onView(
            allOf(
                withId(R.id.owners_property_label),
                isDescendantOfA(withId(R.id.layout_owners_property))
            )
        ).check(matches(withText("Owners Property"))).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.owners_property_icon),
                isDescendantOfA(withId(R.id.layout_owners_property))
            )
        ).check(matches(withDrawable(R.mipmap.owner_property))).check(matches(isDisplayed()))
    }

    @Test
    fun displayGreatNightLifeSection() {
        onView(
            allOf(
                withId(R.id.great_night_life_label),
                isDescendantOfA(withId(R.id.layout_great_night_life))
            )
        ).check(matches(withText("Great Night Life"))).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.great_night_life_icon),
                isDescendantOfA(withId(R.id.layout_great_night_life))
            )
        ).check(matches(withDrawable(R.mipmap.night_life))).check(matches(isDisplayed()))
    }

    @Test
    fun displayFeaturedSectionTitle() {
        assertDisplayed("Featured")
    }

    @Test
    fun displayListOfFeaturedRentList() {

        Thread.sleep(4000)
        assertRecyclerViewItemCount(R.id.featured_rent_list, 5)

        onView(
            allOf(
                withId(R.id.featured_rent_location),
                isDescendantOfA(nthChildOf(withId(R.id.featured_rent_list), 0))
            )
        ).check(matches(withText("Gatthaghar, Bhaktapur"))).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.featured_rent_price),
                isDescendantOfA(nthChildOf(withId(R.id.featured_rent_price), 0))
            )
        ).check(matches(withText("2500"))).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.featured_rent_location),
                isDescendantOfA(nthChildOf(withId(R.id.featured_rent_list), 1))
            )
        ).check(matches(withText("Baneshwor, Kathmandu"))).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.featured_rent_price),
                isDescendantOfA(nthChildOf(withId(R.id.featured_rent_list), 1))
            )
        ).check(matches(withText("2200"))).check(matches(isDisplayed()))
    }
}