package com.example.oficinatiao01.navigation

sealed class Tela (val rota: String) {
    object Principal : Tela ("tela_principal")
    object CadastroPlaca : Tela("tela_cadastro_placa/{placa}"){
        fun criarRota(placa:String) = "tela_cadastro_placa/$placa"
    }

}