package com.example.kotlinpetmanager.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kotlinpetmanager.model.Paciente
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PacienteViewModel : ViewModel() {

    // Lista observable de pacientes expuesta de forma inmutable
    private val _pacientes = MutableStateFlow<List<Paciente>>(emptyList())
    val pacientes: StateFlow<List<Paciente>> = _pacientes.asStateFlow()

    // Estados independientes para el formulario
    var nombre by mutableStateOf("")
        private set

    var especie by mutableStateOf("")
        private set

    var edad by mutableStateOf("")
        private set

    var contactoEmergencia by mutableStateOf("")
        private set

    var esUrgencia by mutableStateOf(false)
        private set

    var errorMensaje by mutableStateOf<String?>(null)
        private set

    private var idCounter = 1

    // Funciones de actualización de estado
    fun onNombreChange(nuevoNombre: String) { nombre = nuevoNombre }
    fun onEspecieChange(nuevaEspecie: String) { especie = nuevaEspecie }
    fun onEdadChange(nuevaEdad: String) {
        if (nuevaEdad.isEmpty() || nuevaEdad.all { it.isDigit() }) {
            edad = nuevaEdad
        }
    }
    fun onContactoChange(nuevoContacto: String) { contactoEmergencia = nuevoContacto }
    fun onEsUrgenciaChange(nuevaUrgencia: Boolean) { esUrgencia = nuevaUrgencia }

    // Validación y adición a la lista
    fun agregarPaciente() {
        if (nombre.isBlank() || especie.isBlank() || edad.isBlank() || contactoEmergencia.isBlank()) {
            errorMensaje = "Por favor completa todos los campos requeridos."
            return
        }

        val edadInt = edad.toIntOrNull()
        if (edadInt == null || edadInt < 0) {
            errorMensaje = "Ingresa una edad válida."
            return
        }

        val nuevoPaciente = Paciente(
            id = idCounter++,
            nombre = nombre.trim(),
            especie = especie.trim(),
            edad = edadInt,
            contactoEmergencia = contactoEmergencia.trim(),
            esUrgencia = esUrgencia
        )

        _pacientes.update { listaActual -> listaActual + nuevoPaciente }
        limpiarFormulario()
    }

    // Limpieza / reset de campos
    fun limpiarFormulario() {
        nombre = ""
        especie = ""
        edad = ""
        contactoEmergencia = ""
        esUrgencia = false
        errorMensaje = null
    }
}