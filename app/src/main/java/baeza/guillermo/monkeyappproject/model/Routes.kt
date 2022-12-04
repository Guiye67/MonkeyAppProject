package baeza.guillermo.monkeyappproject.model

sealed class Routes(val route: String) {
    object Login: Routes("Login")
    object Home: Routes("Home")
    object Register: Routes("Register")
    object Favorites: Routes("Favorites")
    object Addition: Routes("Addition")
    object FullMovie: Routes("FullMovie/{id}") {
        fun createRoute(id: Int) = "FullMovie/$id"
    }
}