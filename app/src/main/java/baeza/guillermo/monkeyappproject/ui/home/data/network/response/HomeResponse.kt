package baeza.guillermo.monkeyappproject.ui.home.data.network.response

import baeza.guillermo.monkeyappproject.model.MediaModel
import com.google.gson.annotations.SerializedName

data class HomeResponse(@SerializedName("success") val getMoviesOk: List<MediaModel>)