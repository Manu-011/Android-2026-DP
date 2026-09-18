package com.example.tiaolista

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiaolista.viewModel.OficinaViewModel

@Composable
fun CadastroPessoa(
    viewModel: OficinaViewModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Cadastro de Cliente",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            OutlinedTextField(
                value = viewModel.cpf,
                onValueChange = {},
                readOnly = true,
                label = { Text("CPF") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = viewModel.nome,
                onValueChange = { viewModel.atualizarNome(it) },
                label = { Text("Nome Completo") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = viewModel.ddd,
                    onValueChange = { viewModel.atualizarDdd(it) },
                    label = { Text("DDD") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(0.3f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    value = viewModel.telefone,
                    onValueChange = { viewModel.atualizarTelefone(it) },
                    label = { Text("Telefone") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(0.7f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { viewModel.incluirPessoa() },
                    colors = ButtonDefaults.buttonColors(containerColor = AmareloClaro)
                ) {
                    Text(text = "Salvar", color = Color.Black)
                }

                Button(
                    onClick = { viewModel.reiniciarTelaPessoa() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text(text = "Cancelar", color = Color.Black)
                }
            }
        }
    }
}