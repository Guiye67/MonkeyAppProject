package baeza.guillermo.monkeyappproject.ui.register.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel: ViewModel() {

    private val _user = MutableLiveData<String>()
    val user: LiveData<String> = _user

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _deportes = MutableLiveData<Boolean>()
    val deportes: LiveData<Boolean> = _deportes

    private val _accion = MutableLiveData<Boolean>()
    val accion: LiveData<Boolean> = _accion

    private val _sifi = MutableLiveData<Boolean>()
    val sifi: LiveData<Boolean> = _sifi

    private val _romance = MutableLiveData<Boolean>()
    val romance: LiveData<Boolean> = _romance

    private val _historicas = MutableLiveData<Boolean>()
    val historicas: LiveData<Boolean> = _historicas

    private val _documentales = MutableLiveData<Boolean>()
    val documentales: LiveData<Boolean> = _documentales

    private val _canRegister = MutableLiveData<Boolean>()
    val canRegister: LiveData<Boolean> = _canRegister

    private val _validEmail = MutableLiveData<Boolean>()
    val validEmail: LiveData<Boolean> = _validEmail

    fun onRegisterChanged(user: String, password: String, email: String) {
        _user.value = user
        _password.value = password
        _email.value = email
        _validEmail.value = isValidEmail(email)
        _canRegister.value = isValidUser(user) && isValidPassword(password) && isValidEmail(email)
    }

    fun onDeportesChanged(deportes: Boolean) { _deportes.value = deportes }
    fun onAccionChanged(accion: Boolean) { _accion.value = accion }
    fun onSifiChanged(sifi: Boolean) { _sifi.value = sifi }
    fun onRomanceChanged(romance: Boolean) { _romance.value = romance }
    fun onHistoricasChanged(historicas: Boolean) { _historicas.value = historicas }
    fun onDocumentalesChanged(documentales: Boolean) { _documentales.value = documentales }

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidUser(user: String): Boolean = user.isNotEmpty()

    private fun isValidPassword(password: String): Boolean = password.isNotEmpty()
}