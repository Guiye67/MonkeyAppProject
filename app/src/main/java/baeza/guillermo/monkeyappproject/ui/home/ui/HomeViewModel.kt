package baeza.guillermo.monkeyappproject.ui.home.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import baeza.guillermo.monkeyappproject.model.MediaModel
import baeza.guillermo.monkeyappproject.ui.home.domain.HomeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val homeUseCase = HomeUseCase()

    private var _movies = MutableLiveData<MutableList<MediaModel>>()
    var movies :LiveData<MutableList<MediaModel>> = _movies

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _desc = MutableLiveData<String>()
    val desc: LiveData<String> = _desc

    private val _score = MutableLiveData<String>()
    val score: LiveData<String> = _score

    private val _category = MutableLiveData<String>()
    val category: LiveData<String> = _category

    private val _drama = MutableLiveData<Boolean>()
    val drama: LiveData<Boolean> = _drama

    private val _crimen = MutableLiveData<Boolean>()
    val crimen: LiveData<Boolean> = _crimen

    private val _fantasia = MutableLiveData<Boolean>()
    val fantasia: LiveData<Boolean> = _fantasia

    private val _aventura = MutableLiveData<Boolean>()
    val aventura: LiveData<Boolean> = _aventura

    private val _accion = MutableLiveData<Boolean>()
    val accion: LiveData<Boolean> = _accion

    private val _cifi = MutableLiveData<Boolean>()
    val cifi: LiveData<Boolean> = _cifi

    init {
        CoroutineScope(Dispatchers.Main).launch {
            _movies.value = homeUseCase.invoke()
            Log.d("Movies recieved", "${movies.value?.size}")
        }
    }

    fun onTitleChanged(title:String) {_title.value = title}
    fun onDescChanged(desc:String) {_desc.value = desc}
    fun onScoreChanged(score:String) {_score.value = score}
    fun onCategoryChanged(category:String) {_category.value = category}
    fun onDramaChanged(drama:Boolean) {_drama.value = drama}
    fun onCrimenChanged(crimen:Boolean) {_crimen.value = crimen}
    fun onFantasiaChanged(fantasia:Boolean) {_fantasia.value = fantasia}
    fun onAventuraChanged(aventura:Boolean) {_aventura.value = aventura}
    fun onAccionChanged(accion:Boolean) {_accion.value = accion}
    fun onCifiChanged(cifi:Boolean) {_cifi.value = cifi}

    fun canCreate(): Boolean {
        return (_title.value!!.isNotEmpty() && _desc.value!!.isNotEmpty() && _score.value!!.isNotEmpty() && _category.value!!.isNotEmpty()
                && (_drama.value == true || _crimen.value == true || _fantasia.value == true || _aventura.value == true || _accion.value == true || _cifi.value == true))
    }

    fun createMovie() {
        var max = 0
        _movies.value!!.forEach {
            if (it.id > max)
                max = it.id
        }

        if (_score.value!!.toInt() < 0)
            _score.value = "0"
        else if (_score.value!!.toInt() > 100)
            _score.value = "100"

        val genres = ArrayList<String>()
        if (_drama.value == true)
            genres.add("Drama")
        if (_crimen.value == true)
            genres.add("Crimen")
        if (_fantasia.value == true)
            genres.add("Fantasía")
        if (_aventura.value == true)
            genres.add("Aventura")
        if (_accion.value == true)
            genres.add("Acción")
        if (_cifi.value == true)
            genres.add("Ciencia Ficción")

        val newMovie = MediaModel(
            id = max+1,
            title = _title.value!!,
            description = _desc.value!!,
            catel = 0,
            score = _score.value!!.toInt(),
            genre = genres,
            category = _category.value!!
        )

        _movies.value!!.add(newMovie)
    }

    fun clar() {
        onTitleChanged("")
        onDescChanged("")
        onScoreChanged("")
        onCategoryChanged("")
        onDramaChanged(false)
        onCrimenChanged(false)
        onFantasiaChanged(false)
        onAventuraChanged(false)
        onAccionChanged(false)
        onCifiChanged(false)
    }
}