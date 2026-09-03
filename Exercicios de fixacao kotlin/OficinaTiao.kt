// 1. Classe representando o Carro
class Carro(
    val proprietario: String,
    val placa: String,
    val ano: Int,
    val modelo: String,
    val problema: String // Ex: "Motor", "Suspensão", "Geral"
) {
    var status: String = "Aguardando Avaliação"

    fun exibirDetalhes() {
        println("  - [$placa] $modelo ($ano) | Proprietário: $proprietario | Problema: $problema | Status: $status")
    }
}

// 2. Classe representando a Oficina do Tião
class Oficina(val nome: String) {
    // Lista para controlar os carros que estão atualmente na oficina
    private val patio = mutableListOf<Carro>()

    // Registrar a ENTRADA de um carro
    fun receberCarro(carro: Carro) {
        patio.add(carro)
        println("🚗 ENTRADA: O veículo $carro.modelo (Placa: $carro.placa) do(a) $carro.proprietario entrou na $nome.")
    }

    // Realizar o serviço (Especialidade: Motor ou Suspensão)
    fun realizarServico(placa: String) {
        val carro = patio.find { it.placa.equals(placa, ignoreCase = true) }

        if (carro != null) {
            when (carro.problema.lowercase()) {
                "motor" -> println("🔧 Tião está retificando/ajustando o MOTOR do ${carro.modelo}...")
                "suspensão", "suspensao" -> println("🛞 Tião está trocando os amortecedores/molas da SUSPENSÃO do ${carro.modelo}...")
                else -> println("⚙️ Tião está fazendo uma revisão geral no ${carro.modelo}...")
            }
            carro.status = "Pronto para Entrega"
        } else {
            println("❌ Veículo com a placa $placa não foi encontrado no pátio.")
        }
    }

    // Registrar a SAÍDA do carro
    fun entregarCarro(placa: String) {
        val carro = patio.find { it.placa.equals(placa, ignoreCase = true) }

        if (carro != null) {
            if (carro.status == "Pronto para Entrega") {
                patio.remove(carro)
                println("✅ SAÍDA: O veículo ${carro.modelo} foi entregue para ${carro.proprietario}. Volte sempre!")
            } else {
                println("⚠️ O carro ${carro.modelo} ainda está em manutenção e não pode ser entregue!")
            }
        } else {
            println("❌ Veículo com a placa $placa não foi encontrado no pátio.")
        }
    }

    // Listar todos os carros no pátio
    fun listarCarrosNoPatio() {
        println("\n--- 📋 PÁTIO DA $nome (${patio.size} veículo(s)) ---")
        if (patio.isEmpty()) {
            println("O pátio está vazio no momento.")
        } else {
            patio.forEach { it.exibirDetalhes() }
        }
        println("-----------------------------------------------\n")
    }
}

// 3. Execução do Programa
fun main() {
    val oficina = Oficina("Oficina Mecânica do Tião")

    // Criando carros
    val carro1 = Carro("Seu Zé", "ABC-1234", 2018, "Gol 1.6", "Motor")
    val carro2 = Carro("Maria", "XYZ-9876", 2021, "Civic", "Suspensão")
    val carro3 = Carro("João", "KOT-2024", 2015, "Palio", "Freios")

    // Entrada de veículos
    oficina.receberCarro(carro1)
    oficina.receberCarro(carro2)
    oficina.receberCarro(carro3)

    // Visualizar o pátio
    oficina.listarCarrosNoPatio()

    // Tião trabalhando nos carros
    oficina.realizarServico("ABC-1234")
    oficina.realizarServico("XYZ-9876")

    // Tentando entregar o carro pronto
    oficina.entregarCarro("ABC-1234")

    // Pátio atualizado após a saída
    oficina.listarCarrosNoPatio()
}