package at.team30.setroute

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import at.team30.setroute.infrastructure.*
import at.team30.setroute.models.FilteringOptions
import at.team30.setroute.ui.routes.RouteListViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FilteringTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockRepository: IRoutesRepository = mockk()
    private val settingRepository : ISettingRepository = SettingRepository()
    private val filteringRepository : IFilterRepository = FilteringRepository()

    @Before
    fun init() {
        every { mockRepository.getRoutes() } returns Fixtures.routes_filtering()
        for (route in Fixtures.routes_filtering()) {
            every { mockRepository.getRoutesById(route.id) } returns route
        }
    }

    @Test
    fun `filter by existing route`(){
        // Arrange
        val sut = RouteListViewModel(mockRepository, settingRepository, filteringRepository)
        filteringRepository.storeFilteringOptions(FilteringOptions(listOf(3)))

        // Act
        val result = sut.getRoutes().value

        // Assert
        assertEquals(1, result?.size)
    }

    @Test
    fun `filter by non-existing route`(){
        // Arrange
        val sut = RouteListViewModel(mockRepository, settingRepository, filteringRepository)
        filteringRepository.storeFilteringOptions(FilteringOptions(listOf(2)))

        // Act
        val result = sut.getRoutes().value

        // Assert
        assertEquals(0, result?.size)
    }

    @Test
    fun `filter by duration`(){
        // Arrange
        val sut = RouteListViewModel(mockRepository, settingRepository, filteringRepository)
        filteringRepository.storeFilteringOptions(FilteringOptions(minDuration = 10f, maxDuration = 20f))

        // Act
        val result = sut.getRoutes().value

        // Assert
        assertEquals(2, result?.size)
    }

    @Test
    fun `filter by duration too strict`(){
        // Arrange
        val sut = RouteListViewModel(mockRepository, settingRepository, filteringRepository)
        filteringRepository.storeFilteringOptions(FilteringOptions(minDuration = 12f, maxDuration = 15f))

        // Act
        val result = sut.getRoutes().value

        // Assert
        assertEquals(0, result?.size)
    }

    @Test
    fun `length by duration`(){
        // Arrange
        val sut = RouteListViewModel(mockRepository, settingRepository, filteringRepository)
        filteringRepository.storeFilteringOptions(FilteringOptions(minDistance = 1f, maxDistance = 2f))

        // Act
        val result = sut.getRoutes().value

        // Assert
        assertEquals(2, result?.size)
    }

    @Test
    fun `length by duration too strict`(){
        // Arrange
        val sut = RouteListViewModel(mockRepository, settingRepository, filteringRepository)
        filteringRepository.storeFilteringOptions(FilteringOptions(minDistance = 3f, maxDistance = 4f))

        // Act
        val result = sut.getRoutes().value

        // Assert
        assertEquals(0, result?.size)
    }
}
