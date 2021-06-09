package at.team30.setroute

import at.team30.setroute.infrastructure.RoutesRepository
import org.junit.Assert.assertEquals
import org.junit.Test

class RoutesRepositoryTest {

    private val sut: RoutesRepository = RoutesRepository()

    @Test
    fun getRoutes_returnsThreeElements() {
        assertEquals(8, sut.getRoutes().size)
    }

    @Test
    fun getRoutes_testDuration() {
        assertEquals(40, sut.getRoutes().elementAt(0).duration)
        assertEquals(60, sut.getRoutes().elementAt(1).duration)
        assertEquals(100, sut.getRoutes().elementAt(2).duration)
        assertEquals(12, sut.getRoutes().elementAt(3).duration)
        assertEquals(100, sut.getRoutes().elementAt(4).duration)
        assertEquals(25, sut.getRoutes().elementAt(5).duration)
        assertEquals(11, sut.getRoutes().elementAt(6).duration)
        assertEquals(40, sut.getRoutes().elementAt(7).duration)
    }

    @Test
    fun getRoutes_testLength() {
        assertEquals(2.5, sut.getRoutes().elementAt(0).length, 0.01)
        assertEquals(3.3, sut.getRoutes().elementAt(1).length, 0.01)
        assertEquals(7.6, sut.getRoutes().elementAt(2).length, 0.01)
        assertEquals(0.7, sut.getRoutes().elementAt(3).length, 0.01)
        assertEquals(7.6, sut.getRoutes().elementAt(4).length, 0.01)
        assertEquals(2.0, sut.getRoutes().elementAt(5).length, 0.01)
        assertEquals(0.6, sut.getRoutes().elementAt(6).length, 0.01)
        assertEquals(3.2, sut.getRoutes().elementAt(7).length, 0.01)
    }
}