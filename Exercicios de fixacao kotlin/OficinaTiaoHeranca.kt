open class Veiculo(
    val proprietario: String,
    val placa: String,
    val ano: Int,
    val modelo: String,
    val problema: String

) {
    var status: String = "Agurdando avaliacao"

    open fun diagnosticarEConsertar() {
        println("🔧 Tião está fazendo uma verificação geral no $modelo...")
        status = "Pronto para entrega"
    }

    open fun exibirDetalhes() {
        print("  - [$placa] $modelo ($ano) | Proprietário: $proprietario | Status: $status")
    }
}

class Carro(
    proprietario: String,
    placa: String,
    ano: Int,
    modelo: String,
    problema: String
    val quantidadePortas: Int
) : Veiculo(proprietario, placa, ano, modelo, problema) {
    override fun diagnosticarEConsertar(){
        super.exibirDetalhes()
        println(" | Cilindradas: ${cilindradas}cc")
    }
}

//...


fun main (){
    val oficina = Oficina("Oficina mecanica do Tiao")

    val meuCarro = Carro("Ana", "ABC-1234", 2021, "Civic", "Suspensão", quantidadePortas = 4)
    val minhaMoto = Moto("Carlos", "XYZ-9876", 2022, "CB 500F", "Motor engasgando", cilindradas = 500)


    oficina.receberVeiculo(meuCarro)
    oficina.receberVeiculo(minhaMoto)

    oficina.realizarServico("ABC-1234")
    oficina.realizarServico("XYZ-9876")

    oficina.listarPatio()
}


