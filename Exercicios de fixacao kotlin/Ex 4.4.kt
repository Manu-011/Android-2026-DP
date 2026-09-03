open class Funcionario(
    val nome : String,
    val salario : Double
)  {
    open fun exibirDados() {
        println("Nome: $nome | Salário: R$ %.2f".format(salario))
    }
}

class Gerente(
    nome: String,
    salario: Double,
    val departamento : String
) : Funcionario(nome, salario) {

    override fun exibirDados() {
        super.exibirDados()
        println("Nome: $nome | Salário: R$ %.2f | Departamento: $departamento".format(salario))
    }
}

fun main() {
    val funcionarioComum = Funcionario(
        nome = "Carlos Silva",
        salario = 3500.00
    )

    val gerenteEngenharia = Gerente(
        nome= "Fernanda Souza",
        salario = 8500.00,
        departamento = "Tecnologia da informacao"
    )
    println("--- Dados do Funcionario ---")
    funcionarioComum.exibirDados()

    println ("\n --- Dados do gerente ---")
    gerenteEngenharia.exibirDados()
}
