package aswanabidin.football_match_finalproject

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testNavigationBehaviour() {
        Espresso.onView(ViewMatchers.withId(R.id.bottom_navigation))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(3000)

        Espresso.onView(ViewMatchers.withText("LAST")).perform(ViewActions.click())
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withText("NEXT")).perform(ViewActions.click())
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.action_search)).perform(ViewActions.click())
        Thread.sleep(1000)

        Espresso.pressBack()
        Thread.sleep(500)

        Espresso.pressBack()
        Thread.sleep(500)

        Espresso.onView(ViewMatchers.withId(R.id.nav_teams)).perform(ViewActions.click())
        Thread.sleep(3000)

        Espresso.onView(ViewMatchers.withId(R.id.action_search)).perform(ViewActions.click())
        Thread.sleep(1000)

        Espresso.pressBack()
        Thread.sleep(500)

        Espresso.pressBack()
        Thread.sleep(500)

        Espresso.onView(ViewMatchers.withId(R.id.nav_favorites)).perform(ViewActions.click())
        Thread.sleep(3000)

        Espresso.onView(ViewMatchers.withText("TEAMS")).perform(ViewActions.click())
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withText("MATCHES")).perform(ViewActions.click())
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.nav_events)).perform(ViewActions.click())
        Thread.sleep(2000)
    }

}
