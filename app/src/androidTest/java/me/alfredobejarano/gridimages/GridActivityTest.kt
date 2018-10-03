package me.alfredobejarano.gridimages

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.swipeUp
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumentation test class that tests if the app fetches the
 * grid of images correctly.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 02/10/2018 - 05:53 PM
 */
@RunWith(AndroidJUnit4::class)
class GridActivityTest {
    /**
     * ActivityTestRule class that allows the initialization of
     * the GridActivity in this test environment.
     */
    @Rule
    @JvmField
    val gridActivityTestRule = ActivityTestRule(GridActivity::class.java)

    /**
     * Opens the app and swipes up in the grid.
     */
    @Test
    fun onAppOpen_displayPicturesGrid() {
        onView(withId(R.id.image_grid)).perform(swipeUp())
    }
}