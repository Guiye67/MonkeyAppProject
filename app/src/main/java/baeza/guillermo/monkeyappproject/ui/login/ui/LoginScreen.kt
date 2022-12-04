package baeza.guillermo.monkeyappproject.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import baeza.guillermo.monkeyappproject.model.Routes
import kotlinx.coroutines.launch
import baeza.guillermo.monkeyappproject.R.drawable.*
import baeza.guillermo.monkeyappproject.ui.theme.*

@Composable
fun MyLogIn(navigationController: NavHostController, scaffoldState: ScaffoldState, viewModel: LoginViewModel) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var recuperar by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val user:String by viewModel.user.observeAsState(initial = "")
    val password:String by viewModel.password.observeAsState(initial = "")
    val canLog:Boolean by viewModel.canLog.observeAsState(initial = false)
    val correo:String by viewModel.correo.observeAsState(initial = "")
    val correoValido:Boolean by viewModel.correoValido.observeAsState(initial = false)

    Box(modifier = Modifier
        .background(customWhite)
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier
            .fillMaxWidth(0.85f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = logo_custom),
                contentDescription = "Monkey Logo",
                modifier = Modifier
                    .size(220.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = user,
                onValueChange = { viewModel.onLoginChanged(it, password, correo) },
                label = { Text("Usuario / Email") },
                leadingIcon = {
                    Icon(painter = painterResource(id = person), contentDescription = "Usuario")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = password,
                onValueChange = { viewModel.onLoginChanged(user, it, correo) },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(
                        onClick = {passwordVisible = !passwordVisible}){
                        if (passwordVisible) {
                            Icon(
                                painter = painterResource(id = eye_cross),
                                contentDescription = "Revelar"
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = eye),
                                contentDescription = "Revelar"
                            )
                        }
                    }
                },
                leadingIcon = {
                    Icon(painter = painterResource(id = lock), contentDescription = "Password")
                },
                modifier = Modifier.fillMaxWidth()
            )
            ClickableText(
                text = AnnotatedString("¿Has olvidado la contraseña?"),
                onClick = {
                    recuperar = true
                },
                modifier = Modifier.align(Alignment.End)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    if (canLog){
                        navigationController.navigate(Routes.Home.route)
                    }else{
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
                    .fillMaxWidth(0.6f)
                    .height(50.dp)
            ) {
                Text(text = "Entrar", color = customWhite)
            }
            Spacer(modifier = Modifier.height(20.dp))
            ClickableText(
                text = AnnotatedString("¿Aún no tienes cuenta? REGISTRATE"),
                onClick = {
                    navigationController.navigate(Routes.Register.route)
                }
            )
        }
    }

    if(recuperar) {
        Dialog(
            onDismissRequest ={
                recuperar = false
                viewModel.onLoginChanged(user, password, "")
            },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Column(
                Modifier
                    .background(customWhite)
                    .padding(end = 20.dp, start = 20.dp, top = 15.dp, bottom = 15.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Introduce tu correo electrónico para recuperar la contraseña",
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = correo,
                    onValueChange = { viewModel.onLoginChanged(user, password, it) },
                    label = { Text("Email") },
                    leadingIcon = {
                        Icon(painter = painterResource(id = mail), contentDescription = "Email")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(top = 15.dp))
                Button(
                    onClick = {
                        viewModel.onLoginChanged(user, password, "")
                        recuperar = false
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                "Recibirás un correo con instrucciones para recuperar la contraseña dentro de poco",
                                duration = SnackbarDuration.Short
                            )
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = customLightBlue),
                    enabled = correoValido
                ) {
                    Text("Enviar", color = customWhite)
                }
            }
        }
    }
}

