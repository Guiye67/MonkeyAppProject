package baeza.guillermo.monkeyappproject.ui.favorites.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import baeza.guillermo.monkeyappproject.model.MediaModel
import baeza.guillermo.monkeyappproject.ui.home.ui.HomeViewModel
import baeza.guillermo.monkeyappproject.ui.home.ui.MediaItem
import baeza.guillermo.monkeyappproject.ui.theme.customWhite

@Composable
fun MyFavorites(navigationController: NavHostController, movieList: MutableList<MediaModel>?) {
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
                if (it.favorite)
                    MediaItem(it, navigationController)
            }
        }
    }
}
