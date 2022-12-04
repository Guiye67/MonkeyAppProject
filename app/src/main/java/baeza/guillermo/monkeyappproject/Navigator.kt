package baeza.guillermo.monkeyappproject

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import baeza.guillermo.monkeyappproject.model.Routes
import baeza.guillermo.monkeyappproject.ui.home.ui.HomeViewModel
import baeza.guillermo.monkeyappproject.ui.screens.*

@Composable
fun CustomNavigator() {
    val navigationController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val main = HomeViewModel()
    val movieList by main.movies.observeAsState()

    NavHost(navController = navigationController, startDestination = Routes.Home.route) {
        composable(route = Routes.Login.route) {
            Login(navigationController = navigationController, scaffoldState)
        }
        composable(route = Routes.Home.route) {
            Home(navigationController = navigationController, scaffoldState, movieList)
        }
        composable(route = Routes.Register.route) {
            Register(navigationController = navigationController, scaffoldState)
        }
        composable(route = Routes.Favorites.route) {
            Favorites(navigationController = navigationController, scaffoldState, movieList)
        }
        composable(route = Routes.Addition.route) {
            Addition(navigationController = navigationController, scaffoldState, main)
        }
        composable(
            route = Routes.FullMovie.route,
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            FullMovie(
                navigationController = navigationController,
                id = navBackStackEntry.arguments?.getInt("id") ?:0,
                scaffoldState,
                main,
                movieList
            )
        }
    }
}
