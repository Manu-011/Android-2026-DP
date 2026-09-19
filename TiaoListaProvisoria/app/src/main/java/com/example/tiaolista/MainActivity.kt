package com.example.tiaolista

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tiaolista.ui.theme.OficinaTiao01Theme
import com.example.tiaolista.viewModel.OficinaViewModel

val AmareloClaro = Color(0xFFE6D690)

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            OficinaTiao01Theme {
                TelaPrincipal()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPrincipal() {
    val viewModel: OficinaViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Oficina do Tião",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "Carros: ${viewModel.contaCarro}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Pessoas: ${viewModel.contaPessoa}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = AmareloClaro)
            )
        }
    ) { paddingValues ->
        OficinaTiaoAbertura(
            modifier = Modifier.padding(paddingValues),
            viewModel = viewModel
        )
    }
}

@Composable
fun OficinaTiaoAbertura(
    modifier: Modifier = Modifier,
    viewModel: OficinaViewModel
) {
    // 1. Exibição das Telas de Cadastro
    if (viewModel.exibirCadastroVeiculo) {
        CadastroVeiculo(viewModel = viewModel, modifier = modifier)
        return
    }

    if (viewModel.exibirCadastroPessoa) {
        CadastroPessoa(viewModel = viewModel, modifier = modifier)
        return
    }

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.carro_do_tiao),
            contentDescription = "Oficina do Tião",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, bottom = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            // 2. Campo Dinâmico (Placa x CPF)
            if (viewModel.modoAtual == OficinaViewModel.ModoTela.VEICULO) {
                OutlinedTextField(
                    value = viewModel.placa,
                    onValueChange = { viewModel.atualizarPlaca(it) },
                    label = { Text("Digite a Placa", fontSize = 20.sp) },
                    textStyle = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color.Black),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = campoCoresCustomizada()
                )
            } else {
                OutlinedTextField(
                    value = viewModel.cpf,
                    onValueChange = { viewModel.atualizarCpf(it) },
                    label = { Text("Digite o CPF", fontSize = 20.sp) },
                    textStyle = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color.Black),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = campoCoresCustomizada()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Botão Buscar
            Button(
                onClick = {
                    if (viewModel.modoAtual == OficinaViewModel.ModoTela.VEICULO) viewModel.buscarPlaca()
                    else viewModel.buscarCpf()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AmareloClaro)
            ) {
                Text("Buscar", color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }

            // 4. Mensagem e Botões de Não Encontrado
            val naoEncontrado = if (viewModel.modoAtual == OficinaViewModel.ModoTela.VEICULO) {
                viewModel.placaNaoEncontrada
            } else {
                viewModel.cpfNaoEncontrado
            }

            if (naoEncontrado) {
                val textoPrompt = if (viewModel.modoAtual == OficinaViewModel.ModoTela.VEICULO) "Cadastrar placa?" else "Cadastrar CPF?"
                Text(text = textoPrompt, fontSize = 32.sp, color = Color.White)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Sim",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Yellow,
                        modifier = Modifier.clickable {
                            if (viewModel.modoAtual == OficinaViewModel.ModoTela.VEICULO) viewModel.abrirCadastroVeiculo()
                            else viewModel.abrirCadastroPessoa()
                        }
                    )

                    Text(
                        text = "Não",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Yellow,
                        modifier = Modifier.clickable {
                            if (viewModel.modoAtual == OficinaViewModel.ModoTela.VEICULO) viewModel.reiniciarTela()
                            else viewModel.reiniciarTelaPessoa()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 5. Botões de Alternância (Veículos / Pessoas)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.alternarModo(OficinaViewModel.ModoTela.VEICULO) },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (viewModel.modoAtual == OficinaViewModel.ModoTela.VEICULO) AmareloClaro else Color.DarkGray
                    )
                ) {
                    Text(
                        text = "Veículos",
                        color = if (viewModel.modoAtual == OficinaViewModel.ModoTela.VEICULO) Color.Black else Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = { viewModel.alternarModo(OficinaViewModel.ModoTela.PESSOA) },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (viewModel.modoAtual == OficinaViewModel.ModoTela.PESSOA) AmareloClaro else Color.DarkGray
                    )
                ) {
                    Text(
                        text = "Pessoas",
                        color = if (viewModel.modoAtual == OficinaViewModel.ModoTela.PESSOA) Color.Black else Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// Função utilitária fora de qualquer composable para reutilização limpa
@Composable
fun campoCoresCustomizada() = OutlinedTextFieldDefaults.colors(
    focusedContainerColor = Color.White.copy(alpha = 0.95f),
    unfocusedContainerColor = Color.White.copy(alpha = 0.95f),
    focusedBorderColor = Color.Black,
    unfocusedBorderColor = Color.DarkGray,
    focusedTextColor = Color.Black,
    unfocusedTextColor = Color.Black,
    focusedLabelColor = Color.Yellow,
    unfocusedLabelColor = Color.DarkGray,
    cursorColor = Color.Black
)