package com.example.oficinatiao01.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.oficinatiao01.model.Veiculo

class OficinaViewModel : ViewModel() {
    private val listaVeiculos =  mutableListOf<Veiculo>()

    var placaNaoEncontrada by mutableStateOf(false)
        private set

    var placa by mutableStateOf("")
        private set
    fun atualizarPlaca(
        texto: String
    ) {
        placa = texto
            .uppercase()
            .filter {
                it.isLetterOrDigit()
            }.take(7)
    }
    fun buscarPlaca() {
        if (placa.isBlank()) {
            return
        }
        val veiculo = listaVeiculos.find {
            it.placa == placa
        }
        placaNaoEncontrada = veiculo == null
    }

    fun reiniciarTela() {
        placa = ""
        placaNaoEncontrada = false
    }
}