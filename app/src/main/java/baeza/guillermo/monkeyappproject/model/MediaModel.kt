package baeza.guillermo.monkeyappproject.model

import androidx.annotation.DrawableRes

data class MediaModel(
    var id:Int,
    var title:String,
    var description:String,
    @DrawableRes var catel:Int,
    var score:Int,
    var genre:List<String>,
    var category:String,
    var favorite:Boolean = false
)
