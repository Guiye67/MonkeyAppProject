package baeza.guillermo.monkeyappproject.ui.addition.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import baeza.guillermo.monkeyappproject.R
import baeza.guillermo.monkeyappproject.model.Routes
import baeza.guillermo.monkeyappproject.ui.home.ui.HomeViewModel
import baeza.guillermo.monkeyappproject.ui.register.ui.MyCheckbox
import baeza.guillermo.monkeyappproject.ui.theme.customLightBlue
import baeza.guillermo.monkeyappproject.ui.theme.customWhite
import kotlinx.coroutines.launch

@Composable
fun MyAdditionScreen(navigationController: NavHostController, scaffoldState: ScaffoldState, viewModel: HomeViewModel) {
    val scope = rememberCoroutineScope()
    val title:String by viewModel.title.observeAsState(initial = "")
    val desc:String by viewModel.desc.observeAsState(initial = "")
    val score:String by viewModel.score.observeAsState("")
    val category:String by viewModel.category.observeAsState(initial = "")
    val drama:Boolean by viewModel.drama.observeAsState(initial = false)
    val crimen:Boolean by viewModel.crimen.observeAsState(initial = false)
    val fantasia:Boolean by viewModel.fantasia.observeAsState(initial = false)
    val aventura:Boolean by viewModel.aventura.observeAsState(initial = false)
    val accion:Boolean by viewModel.accion.observeAsState(initial = false)
    val cifi:Boolean by viewModel.cifi.observeAsState(initial = false)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(customWhite)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 56.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .clip(RoundedCornerShape(10.dp))
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = title,
                onValueChange = { viewModel.onTitleChanged(it) },
                label = { Text(text = "Título") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = desc,
                onValueChange = { viewModel.onDescChanged(it) },
                label = { Text(text = "Descripcion") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = score,
                onValueChange = { viewModel.onScoreChanged(it) },
                label = { Text(text = "Score") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally),
                leadingIcon = {
                    IconButton(onClick = {
                        if (score.isNotEmpty())
                            viewModel.onScoreChanged((score.toInt()-1).toString())
                        else
                            viewModel.onScoreChanged("0")
                    }) {
                        Icon(Icons.Default.Remove, contentDescription = "Decrease")
                    }
                },
                trailingIcon = {
                    IconButton(onClick = {
                        if (score.isNotEmpty())
                            viewModel.onScoreChanged((score.toInt()+1).toString())
                        else
                            viewModel.onScoreChanged("0")
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Increase")
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = category,
                onValueChange = { viewModel.onCategoryChanged(it) },
                label = { Text(text = "Categoría") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Géneros", modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally))
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
            ) {
                MyCheckbox("Drama", state = drama) {viewModel.onDramaChanged(it)}

                Spacer(modifier = Modifier.width(48.dp))

                MyCheckbox("Crimen", state = crimen) {viewModel.onCrimenChanged(it)}
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
            ) {
                MyCheckbox("Fantasía", state = fantasia) {viewModel.onFantasiaChanged(it)}

                Spacer(modifier = Modifier.width(35.dp))

                MyCheckbox("Aventura", state = aventura) {viewModel.onAventuraChanged(it)}
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
            ) {
                MyCheckbox("Acción", state = accion) {viewModel.onAccionChanged(it)}

                Spacer(modifier = Modifier.width(47.dp))

                MyCheckbox("Ciencia Ficción", state = cifi) {viewModel.onCifiChanged(it)}
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    if (viewModel.canCreate()) {
                        viewModel.createMovie()
                        viewModel.clar()
                        navigationController.navigate(Routes.Home.route)
                    } else {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Completar los campos es obligatorio",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = customLightBlue),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Crear Película", color = customWhite)
            }
        }
    }
}