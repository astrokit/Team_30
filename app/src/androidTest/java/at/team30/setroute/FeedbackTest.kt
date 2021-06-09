package at.team30.setroute

import android.Manifest
import android.widget.EditText
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import at.team30.setroute.R
import at.team30.setroute.infrastructure.DependencyInjection
import at.team30.setroute.infrastructure.IRoutesRepository
import at.team30.setroute.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import org.hamcrest.Matchers.not

@UninstallModules(DependencyInjection::class)
@HiltAndroidTest
class FeedbackTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule(order = 2)
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION)

    @Inject
    lateinit var routesRepository: IRoutesRepository

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun feedbackButtonDisabledWithoutText() {
        onView(withId(R.id.settingsFragment)).perform(click())
        onView(withId(R.id.feedback_text_field)).perform(clearText())
        onView(withId(R.id.submit_feedback_button)).check(matches(not(isEnabled())))
    }

    @Test
    fun feedbackButtonDisabledWithLongText() {
        onView(withId(R.id.settingsFragment)).perform(click())
        onView(withId(R.id.feedback_text_field)).perform(clearText(), typeText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea"))
        onView(withId(R.id.submit_feedback_button)).check(matches(not(isEnabled())))
    }

    @Test
    fun feedbackButtonEnabled() {
        onView(withId(R.id.settingsFragment)).perform(click())
        onView(withId(R.id.feedback_text_field)).perform(clearText(), typeText("Feedback"))
        onView(withId(R.id.submit_feedback_button)).check(matches(isEnabled()))
    }
}