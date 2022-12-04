package baeza.guillermo.monkeyappproject.ui.home.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import baeza.guillermo.monkeyappproject.R
import baeza.guillermo.monkeyappproject.model.MediaModel
import baeza.guillermo.monkeyappproject.model.Routes
import baeza.guillermo.monkeyappproject.ui.theme.*

@Composable
fun MyHome(navigationController: NavHostController, movieList: MutableList<MediaModel>?) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
            .background(customWhite)
    ) {
        item {
            movieList?.forEach {
                MediaItem(it, navigationController)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MediaItem(mediaModel:MediaModel, navigationController: NavHostController) {
    var expandedState by rememberSaveable{ mutableStateOf(false) }
    var cardHeight by rememberSaveable{ mutableStateOf(150) }
    var iconSeparation by rememberSaveable{ mutableStateOf(30) }

    Card(
        border = BorderStroke(2.dp, customLightBlue),
        modifier = Modifier
            .height(cardHeight.dp)
            .background(customWhite),
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(customWhite)
        ){
            Image(
                painterResource(id = obtenerImagen(mediaModel.catel)),
                contentDescription = mediaModel.title,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.3f),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.85f)
                    .padding(start = 3.dp)
            ){
                Text(
                    text = mediaModel.title,
                    modifier = Modifier
                        .padding(top = 4.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                if (expandedState) {
                    Row {
                        var genres = ""
                        for (i in 0 until mediaModel.genre.size) {
                            genres += mediaModel.genre.get(i)
                            if (i != mediaModel.genre.size - 1) {
                                genres += ", "
                            }
                        }
                        Text(text = "Géneros: ", fontWeight = FontWeight.Bold)
                        Text(text = genres)
                    }
                    Text(
                        text = mediaModel.description,
                        fontSize = 17.sp,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                    )
                } else {
                    Text(
                        text = mediaModel.description,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Justify
                    )
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterVertically)
            ) {
                Spacer(modifier = Modifier.height(iconSeparation.dp))
                val starIcon = if (mediaModel.favorite)
                    Icons.Filled.Star
                else
                    Icons.Outlined.StarOutline
                IconButton(
                    onClick = {
                        mediaModel.favorite = !mediaModel.favorite
                        refreshView(navigationController)
                    }
                ) {
                    Icon(starIcon, contentDescription = "Star", tint = customLightBlue, modifier = Modifier.size(34.dp))
                }
                IconButton(
                    onClick = {
                        navigationController.navigate(Routes.FullMovie.createRoute(mediaModel.id))
                    }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add", tint = customLightBlue, modifier = Modifier.size(40.dp))
                }
            }
        }
    }
    if (expandedState) {
        cardHeight = 250
        iconSeparation = 80
    } else {
        cardHeight = 150
        iconSeparation = 30
    }
}

fun obtenerImagen(id : Int) : Int{
    var imagen = R.drawable.ic_launcher_background
    when(id){
        1 -> imagen = R.drawable.c1
        2 -> imagen = R.drawable.c2
        3-> imagen = R.drawable.c3
        4-> imagen = R.drawable.c4
        5-> imagen = R.drawable.c5
        6-> imagen = R.drawable.c6
        7-> imagen = R.drawable.c7
        8-> imagen = R.drawable.c8
        9-> imagen = R.drawable.c9
        10-> imagen = R.drawable.c10
        0 -> imagen = R.drawable.ic_launcher_background
    }
    return imagen
}

fun refreshView(navigationController: NavHostController) {
    val idView = navigationController.currentDestination?.id
    navigationController.navigate(idView!!)
    navigationController.popBackStack(idView, inclusive = true)
}