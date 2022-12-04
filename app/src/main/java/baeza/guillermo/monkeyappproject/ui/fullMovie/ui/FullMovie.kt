package baeza.guillermo.monkeyappproject.ui.fullMovie.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import baeza.guillermo.monkeyappproject.R
import baeza.guillermo.monkeyappproject.model.MediaModel
import baeza.guillermo.monkeyappproject.ui.home.ui.HomeViewModel
import baeza.guillermo.monkeyappproject.ui.theme.customWhite

@Composable
fun MyFullMovie(navigationController: NavHostController, scaffoldState: ScaffoldState, main: HomeViewModel, movie: MediaModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(customWhite)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 56.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)) {
            Image(
                painter = painterResource(id = obtenerImagen(movie.catel)),
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .clip(RoundedCornerShape(10.dp))
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = movie.title.toUpperCase(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 10.dp),
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(10.dp))
            var genres = ""
            for (i in 0 until movie.genre.size) {
                genres += movie.genre.get(i)
                if (i != movie.genre.size-1)
                    genres += " | "
            }
            Text(
                text = genres,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 20.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = "Categoría: ", fontWeight = FontWeight.Bold)
                Text(text = movie.category)
                Spacer(modifier = Modifier.width(20.dp))
                Icon(Icons.Default.Favorite, contentDescription = "Score")
                Text(text = movie.score.toString(), modifier = Modifier.padding(start = 3.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = movie.description,
                fontSize = 18.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )
        }
    }
}

fun obtenerImagen(id : Int) : Int {
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