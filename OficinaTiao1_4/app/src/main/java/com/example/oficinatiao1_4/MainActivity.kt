package com.example.oficinatiao1_4

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oficinatiao1_4.ui.theme.OficinaTiao1_4Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OficinaTiao1_4Theme() {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    var telaAtual by remember { mutableStateOf("inicio") }
                }
                when (telaAtual) {
                    "inicio" -> OficinaTiaoAbertura(
                        onIrParaCadastro = { telaAtual = "cadastro" }
                    )

                    "cadastro" -> CadastroVeiculoScreen(
                        onVoltar = { telaAtual = "inicio" }
                    )
                }
            }
        }
    }
}

//Tela Inicial //
@Composable
fun OficinaTiaoAbertura(onIrParaCadastro: () -> Unit) {
    var contador by remember { mutableStateOf(0) }

    // Box(modifier = Modifier.fillMaxSize()) {
    //image(

    // )
//}

    Column(
        modifier = Modifier.align(Alignment.Center),
        horizontalAlignment = alignment.centerHorizontally
    ) {
        Text(
            text = "Oficina do Tião"
                    color = Color . Cyan,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { contador++ },
            colors = ButtonsDefaults.buttonColors(containerColor = Color.LightGray)
        ) {
            Text(
                text = "Clique Aqui",
                color = Color.Cyan,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height (8.dp))

        Contador(contador)

        Spacer(modifier = Modifier.height(32.dp))

        // Botão para ir para a tela de cadastro
        Button(
            onClick = onIrParaCadastro,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text(
                text = "Cadastro Veiculo",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = fontWeight.Bold
            )
        }

    }
}

@Composable
fun Contador(contador: Int) {

}