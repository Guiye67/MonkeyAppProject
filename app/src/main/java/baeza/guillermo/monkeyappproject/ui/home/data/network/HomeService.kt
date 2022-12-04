package baeza.guillermo.monkeyappproject.ui.home.data.network

import baeza.guillermo.monkeyappproject.core.network.RetrofitHelper
import baeza.guillermo.monkeyappproject.model.MediaModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getMovies(): List<MediaModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(HomeClient::class.java).getMovies()
            response.body()?.getMoviesOk ?: emptyList()
        }
    }
}