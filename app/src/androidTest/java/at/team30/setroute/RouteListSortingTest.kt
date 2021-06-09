package at.team30.setroute

import android.Manifest
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import at.team30.setroute.infrastructure.DependencyInjection
import at.team30.setroute.infrastructure.IRoutesRepository
import at.team30.setroute.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.hamcrest.Matchers.anything
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@UninstallModules(DependencyInjection::class)
@HiltAndroidTest
class RouteListSortingTest {

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
    fun sort_dialog_is_shown() {
        //Arrange
        val routes = routesRepository.getRoutes()
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        //Act
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(context.resources.getString(R.string.sort)))
            .perform(click())
        onView(withText(context.resources.getString(R.string.ascending)))
            .perform(click())
        onView(withText(context.resources.getString(R.string.apply)))
            .perform(click())

        // Assert
        onData(anything()).inAdapterView(withId(R.id.list)).atPosition(0)
            .onChildView(withId(R.id.name))
            .check(matches(withSubstring(routes[0].name)))
        onData(anything()).inAdapterView(withId(R.id.list)).atPosition(1)
            .onChildView(withId(R.id.name))
            .check(matches(withSubstring(routes[1].name)))
        onData(anything()).inAdapterView(withId(R.id.list)).atPosition(2)
            .onChildView(withId(R.id.name))
            .check(matches(withSubstring(routes[2].name)))
    }
}