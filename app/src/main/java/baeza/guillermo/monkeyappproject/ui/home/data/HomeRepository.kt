package baeza.guillermo.monkeyappproject.ui.home.data

import baeza.guillermo.monkeyappproject.model.MediaModel
import baeza.guillermo.monkeyappproject.ui.home.data.network.HomeService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeRepository {
    private val api = HomeService()

    suspend fun getMovies(): ArrayList<MediaModel> {
        val itemsArray : ArrayList<MediaModel> = ArrayList()
        CoroutineScope(Dispatchers.IO).launch {
            val items = api.getMovies()

            withContext(Dispatchers.IO){
                for(i in 0 until items.count()){
                    val id = items[i].id
                    val title = items[i].title
                    val desc = items[i].description
                    val cartel = items[i].catel
                    val score = items[i].score
                    val genre = items[i].genre
                    val category = items[i].category
                    itemsArray.add(MediaModel(id, title, desc, cartel, score, genre, category))
                }
            }
        }
        return itemsArray
    }
}