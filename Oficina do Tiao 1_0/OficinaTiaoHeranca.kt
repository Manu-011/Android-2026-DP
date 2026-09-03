open class Veiculo(
    val proprietario: String
    val placa: String
    val ano: Int
    val modelo: String,
    val problema: String

) {
    var status: String = "Aguardando Avaliacao"

    open fun diagnosticarEConsertar(){
        println(" Tião está fazendo uma verificação geral no \$modelo...\"")
        status = "Pronto para Entregar"
    }
}

