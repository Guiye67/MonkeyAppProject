package baeza.guillermo.monkeyappproject.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import baeza.guillermo.monkeyappproject.model.MediaModel
import baeza.guillermo.monkeyappproject.model.Routes
import baeza.guillermo.monkeyappproject.ui.addition.ui.MyAdditionScreen
import baeza.guillermo.monkeyappproject.ui.favorites.ui.MyFavorites
import baeza.guillermo.monkeyappproject.ui.fullMovie.ui.MyFullMovie
import baeza.guillermo.monkeyappproject.ui.home.ui.HomeViewModel
import baeza.guillermo.monkeyappproject.ui.home.ui.MyHome
import baeza.guillermo.monkeyappproject.ui.login.ui.*
import baeza.guillermo.monkeyappproject.ui.register.ui.MyRegister
import baeza.guillermo.monkeyappproject.ui.register.ui.RegisterViewModel
import baeza.guillermo.monkeyappproject.ui.theme.customDarkBlue
import baeza.guillermo.monkeyappproject.ui.theme.customGreen
import baeza.guillermo.monkeyappproject.ui.theme.customLightBlue
import baeza.guillermo.monkeyappproject.ui.theme.customWhite

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Login(navigationController: NavHostController, scaffoldState: ScaffoldState) {
    Scaffold(scaffoldState = scaffoldState,
        content = {
            MyLogIn(navigationController, scaffoldState, LoginViewModel())
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home(navigationController: NavHostController, scaffoldState: ScaffoldState, movieList: MutableList<MediaModel>?) {
    Scaffold(scaffoldState = scaffoldState,
        topBar = { MyTopNav("Home") },
        bottomBar = { MyBottomNav(navigationController = navigationController, "Home") },
        content = {
            MyHome(navigationController, movieList)
        },
        floatingActionButton = {
            MyActionButton(navigationController)
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Register(navigationController: NavHostController, scaffoldState: ScaffoldState) {
    Scaffold(scaffoldState = scaffoldState,
        content = {
            MyRegister(navigationController, scaffoldState, RegisterViewModel())
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Favorites(navigationController: NavHostController, scaffoldState: ScaffoldState, movieList: MutableList<MediaModel>?) {
    Scaffold(scaffoldState = scaffoldState,
        topBar = { MyTopNav("Favorites") },
        bottomBar = { MyBottomNav(navigationController = navigationController, "Favorites") },
        content = {
            MyFavorites(navigationController, movieList)
        },
        floatingActionButton = {
            MyActionButton(navigationController)
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Addition(navigationController: NavHostController, scaffoldState: ScaffoldState, main: HomeViewModel) {
    Scaffold(scaffoldState = scaffoldState,
        topBar = { MyTopNav("Movie Addition") },
        bottomBar = { MyBottomNav(navigationController = navigationController, "Movie Addition") },
        content = {
            MyAdditionScreen(navigationController, scaffoldState, main)
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FullMovie(navigationController: NavHostController, id: Int, scaffoldState: ScaffoldState, main: HomeViewModel, movieList: MutableList<MediaModel>?) {
    var movie: MediaModel = movieList!![0]
    movieList.forEach {
        if (it.id == id)
            movie = it
    }

    Scaffold(scaffoldState = scaffoldState,
        topBar = { MyTopNav(movie.title) },
        bottomBar = { MyBottomNav(navigationController = navigationController, movie.title) },
        content = {
            MyFullMovie(navigationController, scaffoldState, main, movie)
        }
    )
}

@Composable
fun MyBottomNav(navigationController: NavHostController, vista: String){
    val homeIcon:ImageVector
    val starIcon:ImageVector
    when (vista) {
        "Home" -> {
            homeIcon = Icons.Default.Home
            starIcon = Icons.Outlined.StarOutline
        }
        "Favorites" -> {
            homeIcon = Icons.Outlined.Home
            starIcon = Icons.Default.Star
        }
        else -> {
            homeIcon = Icons.Outlined.Home
            starIcon = Icons.Outlined.StarOutline
        }
    }
    BottomNavigation(
        backgroundColor = customDarkBlue,
        contentColor = Color.White
    ) {
        BottomNavigationItem(
            selected = false,
            icon = {
                Icon(
                    imageVector = homeIcon,
                    contentDescription = "Home",
                    tint = customLightBlue
                )
            },
            onClick = {
                if (vista == "Home")
                    refreshView(navigationController)
                else
                    navigationController.navigate(Routes.Home.route)
            }
        )
        BottomNavigationItem(
            selected = false,
            icon = {
                Icon(
                    imageVector = starIcon,
                    contentDescription = "Favorites",
                    tint = customLightBlue
                )
            },
            onClick = {
                if (vista == "Favorites")
                    refreshView(navigationController)
                else
                    navigationController.navigate(Routes.Favorites.route)
            }
        )
    }
}

@Composable
fun MyTopNav(vista: String){
    TopAppBar(
        title = { Text(text = "Monkey Films - $vista", color = customWhite) },
        backgroundColor = customDarkBlue,
        contentColor = Color.White,
        elevation = 123.dp,
        modifier = Modifier.height(60.dp),
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu", tint = customLightBlue)
            }
        }
    )
}

fun refreshView(navigationController: NavHostController) {
    val idView = navigationController.currentDestination?.id
    navigationController.navigate(idView!!)
    navigationController.popBackStack(idView, inclusive = true)
}

@Composable
fun MyActionButton(navigationController: NavHostController) {
    FloatingActionButton(
        onClick = {
            navigationController.navigate(Routes.Addition.route)
        },
        backgroundColor = customGreen
    ) {
        Icon(Icons.Default.Add, contentDescription = "Add", tint = customWhite)
    }
}