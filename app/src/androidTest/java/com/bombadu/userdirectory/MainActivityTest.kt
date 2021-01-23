package com.bombadu.userdirectory

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Before
    fun setup() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun test_FabIsVisible() {
        onView(withId(R.id.floatingActionButton)).check(matches(isDisplayed()))
    }

    /*@Test
    fun clickFab_VerifyNavigationToAddScreen() {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withId(R.id.))
        //intended(hasComponent(AddUserActivity::class.java.name))
    }*/

}