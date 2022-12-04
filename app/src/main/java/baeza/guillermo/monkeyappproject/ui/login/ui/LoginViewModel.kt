package baeza.guillermo.monkeyappproject.ui.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    private val _user = MutableLiveData<String>()
    val user: LiveData<String> = _user

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _canLog = MutableLiveData<Boolean>()
    val canLog: LiveData<Boolean> = _canLog

    private val _correo = MutableLiveData<String>()
    val correo: LiveData<String> = _correo

    private val _correoValido = MutableLiveData<Boolean>()
    val correoValido: LiveData<Boolean> = _correoValido

    fun onLoginChanged(user: String, password: String, correo: String) {
        _user.value = user
        _password.value = password
        _canLog.value = isValidUser(user) && isValidPassword(password)
        _correo.value = correo
        _correoValido.value = isValidEmail(correo)
    }

    private fun isValidUser(user: String): Boolean = user.length > 1 //Cambiar adecuadamente en un futuro

    private fun isValidPassword(password: String): Boolean = password.length > 1 //Cambiar adecuadamente en un futuro

    private fun isValidEmail(correo: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(correo).matches()
}