package com.example.tiaolista.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tiaolista.model.Pessoa
import com.example.tiaolista.model.Veiculo


class OficinaViewModel : ViewModel() {
    private val listaVeiculos = mutableListOf<Veiculo>()

    var contaCarro by mutableStateOf(0)
        private set
    var placaNaoEncontrada by mutableStateOf(false)
        private set

    var exibirCadastroVeiculo by mutableStateOf(false)
        private set

    var placa by mutableStateOf("")
        private set

    var marca by mutableStateOf("")
        private set

    var modelo by mutableStateOf("")
        private set

    var ano by mutableStateOf("")
        private set

    var idTipoCombustivel by mutableStateOf(1)
        private set

    fun atualizarPlaca(texto: String) {
        placa = texto
            .uppercase()
            .filter { it.isLetterOrDigit() }
            .take(7)
    }

    fun atualizarMarca(texto: String) {
        marca = texto
    }

    fun atualizarModelo(texto: String) {
        modelo = texto
    }

    fun atualizarAno(texto: String) {
        ano = texto.filter { it.isDigit() }.take(4)
    }

    fun atualizarTipoCombustivel(id: Int) {
        idTipoCombustivel = id
    }

    fun buscarPlaca() {
        if (placa.isBlank()) {
            return
        }

        val veiculoEncontrado = listaVeiculos.find {
            it.placa.uppercase() == placa.uppercase()
        }

        if (veiculoEncontrado == null) {
            placaNaoEncontrada = true
        } else {
            placaNaoEncontrada = false
            marca = veiculoEncontrado.marca
            modelo = veiculoEncontrado.modelo
            ano = veiculoEncontrado.ano.toString()
            idTipoCombustivel =
                veiculoEncontrado.idTipoCombustivel
            exibirCadastroVeiculo = true
        }
    }

    fun abrirCadastroVeiculo() {
        exibirCadastroVeiculo = true
    }

    fun incluirVeiculo() {
        val anoInt = ano.toIntOrNull() ?: return
        val veiculoAchado = listaVeiculos.find {
            it.placa.uppercase() == placa.uppercase()
        }

        if (veiculoAchado != null) {
            reiniciarTela()
            return
        }

        listaVeiculos.add(
            Veiculo(
                placa = placa,
                marca = marca,
                modelo = modelo,
                ano = anoInt,
                idTipoCombustivel = idTipoCombustivel
            )
        )

        contaCarro++
        reiniciarTela()
    }

    fun reiniciarTela() {
        placa = ""
        marca = ""
        modelo = ""
        ano = ""

        placaNaoEncontrada = false
        exibirCadastroVeiculo = false

        idTipoCombustivel = 1
    }

    // --- Adicionar à OficinaViewModel ---

    private val listaPessoas = mutableListOf<Pessoa>()

    var contaPessoa by mutableStateOf(0)
        private set

    var exibirCadastroPessoa by mutableStateOf(false)
        private set

    var cpfNaoEncontrado by mutableStateOf(false)
        private set

    var cpf by mutableStateOf("")
        private set

    var nome by mutableStateOf("")
        private set

    var ddd by mutableStateOf("")
        private set

    var telefone by mutableStateOf("")
        private set

// --- Funções de Validação de Texto ---

    fun atualizarCpf(texto: String) {
        cpf = texto.filter { it.isDigit() }.take(11)
    }

    fun atualizarNome(texto: String) {
        nome = texto
    }

    fun atualizarDdd(texto: String) {
        ddd = texto.filter { it.isDigit() }.take(2)
    }

    fun atualizarTelefone(texto: String) {
        telefone = texto.filter { it.isDigit() }.take(9)
    }

// --- Funções de Negócio ---

    fun buscarCpf() {
        if (cpf.isBlank()) return

        val pessoaEncontrada = listaPessoas.find { it.cpf == cpf }

        if (pessoaEncontrada == null) {
            cpfNaoEncontrado = true
        } else {
            cpfNaoEncontrado = false
            nome = pessoaEncontrada.nome
            ddd = pessoaEncontrada.ddd
            telefone = pessoaEncontrada.telefone
            exibirCadastroPessoa = true
        }
    }

    fun abrirCadastroPessoa() {
        exibirCadastroPessoa = true
    }

    fun incluirPessoa() {
        if (cpf.length < 11 || nome.isBlank()) return

        val pessoaExistente = listaPessoas.find { it.cpf == cpf }

        if (pessoaExistente != null) {
            reiniciarTelaPessoa()
            return
        }

        listaPessoas.add(
            Pessoa(
                cpf = cpf,
                nome = nome,
                ddd = ddd,
                telefone = telefone
            )
        )

        contaPessoa++
        reiniciarTelaPessoa()
    }

    fun reiniciarTelaPessoa() {
        cpf = ""
        nome = ""
        ddd = ""
        telefone = ""
        cpfNaoEncontrado = false
        exibirCadastroPessoa = false
    }

    enum class ModoTela { VEICULO, PESSOA }

    var modoAtual by mutableStateOf(ModoTela.VEICULO)
        private set

    fun alternarModo(novoModo: ModoTela) {
        reiniciarTela()
        reiniciarTelaPessoa()
        modoAtual = novoModo
    }
}




