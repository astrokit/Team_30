package at.team30.setroute

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import at.team30.setroute.infrastructure.*
import at.team30.setroute.models.Field
import at.team30.setroute.models.Order
import at.team30.setroute.models.SortingOptions
import at.team30.setroute.ui.routes.RouteListViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertArrayEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
class SortingTest (
    private val order: Order,
    private val field: Field,
    private val expectedIds: Array<Int>
) {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockRepository: IRoutesRepository = mockk()
    private val settingRepository : ISettingRepository = SettingRepository()
    private val filteringRepository : IFilterRepository = FilteringRepository()

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(Order.ASCENDING, Field.TITLE, arrayOf(1,2,3)),
                arrayOf(Order.DESCENDING, Field.TITLE, arrayOf(3,2,1)),
                arrayOf(Order.ASCENDING, Field.DURATION, arrayOf(3,1,2)),
                arrayOf(Order.DESCENDING, Field.DURATION, arrayOf(2,1,3)),
                arrayOf(Order.ASCENDING, Field.DISTANCE, arrayOf(2,3,1)),
                arrayOf(Order.DESCENDING, Field.DISTANCE, arrayOf(1,3,2))
            )
        }
    }

    @Before
    fun init() {
        settingRepository.storeSortingOptions(SortingOptions(order, field))
        every { mockRepository.getRoutes() } returns Fixtures.routes_sorting()
        for (route in Fixtures.routes_sorting()) {
            every { mockRepository.getRoutesById(route.id) } returns route
        }
    }

    @Test
    fun routes_sorted() {
        // Arrange
        val sut = given_viewModel()

        // Act
        val result = sut.getRoutes().value

        // Assert
        assertArrayEquals(
            expectedIds,
            result?.map { it.id }?.toTypedArray()
        )
    }

    private fun given_viewModel(): RouteListViewModel {
        return RouteListViewModel(mockRepository, settingRepository, filteringRepository)
    }
}