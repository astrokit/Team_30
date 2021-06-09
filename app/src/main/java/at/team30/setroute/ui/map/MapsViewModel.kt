package at.team30.setroute.ui.map

import androidx.lifecycle.ViewModel
import at.team30.setroute.infrastructure.IRoutesRepository
import at.team30.setroute.models.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MapsViewModel @Inject constructor (
    private val routesRepository : IRoutesRepository
) : ViewModel() {
    fun getRoutes() : List<Route> {
        return routesRepository.getRoutes()
    }
}