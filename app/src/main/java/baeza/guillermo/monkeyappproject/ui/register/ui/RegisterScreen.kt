package baeza.guillermo.monkeyappproject.ui.register.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import baeza.guillermo.monkeyappproject.R
import baeza.guillermo.monkeyappproject.model.Routes
import baeza.guillermo.monkeyappproject.ui.theme.customLightBlue
import baeza.guillermo.monkeyappproject.ui.theme.customWhite
import kotlinx.coroutines.launch

@Composable
fun MyRegister(navigationController: NavHostController, scaffoldState: ScaffoldState, viewModel: RegisterViewModel) {
    val maxChar = 50
    val scope = rememberCoroutineScope()
    var snackbarText:String

    val user:String by viewModel.user.observeAsState(initial = "")
    val password:String by viewModel.password.observeAsState(initial = "")
    var password2 by rememberSaveable { mutableStateOf("") }
    val email:String by viewModel.email.observeAsState(initial = "")
    val validEmail:Boolean by viewModel.validEmail.observeAsState(initial = false)
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var passwordVisible2 by rememberSaveable { mutableStateOf(false) }

    val deportes:Boolean by viewModel.deportes.observeAsState(initial = false)
    val accion:Boolean by viewModel.accion.observeAsState(initial = false)
    val sifi:Boolean by viewModel.sifi.observeAsState(initial = false)
    val romance:Boolean by viewModel.romance.observeAsState(initial = false)
    val historicas:Boolean by viewModel.historicas.observeAsState(initial = false)
    val documentales:Boolean by viewModel.documentales.observeAsState(initial = false)
    val canRegister:Boolean by viewModel.canRegister.observeAsState(initial = false)

    Box(
        modifier = Modifier
            .background(customWhite)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_custom),
                contentDescription = "Monkey Logo",
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(15.dp))
            TextField(
                value = user,
                onValueChange = {
                    if (it.length <= maxChar) {
                        viewModel.onRegisterChanged(it, password, email)
                    }
                },
                label = { Text(text = "Usuario") },
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(0.8f),
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.person), contentDescription = "Usuario")
                }
            )
            Text(
                text = "${user.length} / $maxChar",
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = email,
                onValueChange = {
                    if (it.length <= maxChar) {
                        viewModel.onRegisterChanged(user, password, it)
                    }
                },
                label = { Text(text = "Email") },
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(0.8f),
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.mail), contentDescription = "Email")
                }
            )
            Text(
                text = "${email.length} / $maxChar",
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = password,
                onValueChange = {
                    if (it.length <= maxChar) {
                        viewModel.onRegisterChanged(user, it, email)
                    }
                },
                label = { Text(text = "Contraseña") },
                modifier = Modifier.fillMaxWidth(0.8f),
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.lock), contentDescription = "Password")
                },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(
                        onClick = {passwordVisible = !passwordVisible}){
                        if (passwordVisible) {
                            Icon(
                                painter = painterResource(id = R.drawable.eye_cross),
                                contentDescription = "Revelar"
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.eye),
                                contentDescription = "Revelar"
                            )
                        }
                    }
                }
            )
            Text(
                text = "${password.length} / $maxChar",
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = password2,
                onValueChange = {
                    if (it.length <= maxChar) {
                        password2 = it
                    }
                },
                label = { Text(text = "Repite la contraseña") },
                modifier = Modifier.fillMaxWidth(0.8f),
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.lock), contentDescription = "Password")
                },
                singleLine = true,
                visualTransformation = if (passwordVisible2) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(
                        onClick = {passwordVisible2 = !passwordVisible2}){
                        if (passwordVisible2) {
                            Icon(
                                painter = painterResource(id = R.drawable.eye_cross),
                                contentDescription = "Revelar"
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.eye),
                                contentDescription = "Revelar"
                            )
                        }
                    }
                }
            )
            Text(
                text = "${password2.length} / $maxChar",
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Intereses",
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                MyCheckbox("Deportes", state = deportes) {viewModel.onDeportesChanged(it)}

                Spacer(modifier = Modifier.width(37.dp))

                MyCheckbox("Romance", state = romance) {viewModel.onRomanceChanged(it)}
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                MyCheckbox("Acción", state = accion) {viewModel.onAccionChanged(it)}

                Spacer(modifier = Modifier.width(54.dp))

                MyCheckbox("Históricas", state = historicas) {viewModel.onHistoricasChanged(it)}
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                MyCheckbox("Si-Fi", state = sifi) {viewModel.onSifiChanged(it)}

                Spacer(modifier = Modifier.width(73.dp))

                MyCheckbox("Documentales", state = documentales) {viewModel.onDocumentalesChanged(it)}
            }

            Spacer(modifier = Modifier.height(35.dp))

            Button(
                onClick = {
                    if (canRegister && password == password2) {
                        navigationController.navigate(Routes.Home.route)
                    } else {
                        snackbarText = if (email.isNotEmpty() && !validEmail)
                            "El correo electrónico no tiene el formato correcto"
                        else if (password.isNotEmpty() && password2.isNotEmpty() && password != password2)
                            "Las contraseñas no coinciden"
                        else
                            "Completar los campos es obligatorio"
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = snackbarText,
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = customLightBlue),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                Text(text = "Registrarse", color = customWhite)
            }
        }
    }

}

@Composable
fun MyCheckbox(texto:String, state:Boolean, onCheckedChange: (Boolean) -> Unit) {
    Checkbox(
        checked = state,
        onCheckedChange = { onCheckedChange(!state) },
        colors = CheckboxDefaults.colors(checkedColor = customLightBlue)
    )
    Text(text = texto, modifier = Modifier.padding(top = 13.dp))
}
