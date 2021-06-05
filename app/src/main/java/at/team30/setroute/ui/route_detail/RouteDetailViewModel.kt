package at.team30.setroute.ui.route_detail


import android.graphics.Bitmap
import android.graphics.Picture
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.lifecycle.*
import at.team30.setroute.R
import at.team30.setroute.infrastructure.IImageRepository
import at.team30.setroute.infrastructure.IRoutesRepository
import at.team30.setroute.models.Route
import com.google.android.material.button.MaterialButtonToggleGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class RouteDetailViewModel @Inject constructor(
    private val routesRepository : IRoutesRepository,
    private val imageRepository : IImageRepository
) : ViewModel() {

    private var routeId = 0;
    private lateinit var image: LiveData<Bitmap?>

    fun getImageLiveData(): LiveData<Bitmap?> {
        if(!this::image.isInitialized)
        {
            image = liveData {
                try{
                    val data = getImage(routeId)
                    emit(data)
                }catch(error: IOException){
                    emit(null)
                }
            }
        }
        return image;
    }

    fun getRoute(id: Int): Route? {
        return routesRepository.getRoutesById(id)
    }

    private suspend fun getImage(routeId: Int) = withContext(Dispatchers.IO){
        return@withContext imageRepository.getImage(routeId)
    }

    fun setId(routeIdSet: Int) {
        routeId = routeIdSet;
    }
}