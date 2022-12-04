package baeza.guillermo.monkeyappproject.ui.home.domain

import baeza.guillermo.monkeyappproject.model.MediaModel
import baeza.guillermo.monkeyappproject.ui.home.data.HomeRepository

class HomeUseCase {
    private val repository = HomeRepository()

    suspend operator fun invoke(): ArrayList<MediaModel> {
        return repository.getMovies()
    }
}