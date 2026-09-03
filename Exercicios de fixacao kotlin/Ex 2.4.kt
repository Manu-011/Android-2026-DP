fun classificarIdade(idade: Int) {
    when(idade) {
        in 0.. 12 -> println ("Crianca")
        in 13.. 17 -> println ("Adolescente")
        in 18.. 64 -> println ("Adulto")
        in 65..Int.MAX_VALUE -> println("Idoso")
        else -> println ("Idade invalida")
    }
}
fun main() {
    classificarIdade(8) // Criança
    classificarIdade(15) //Adolescente
    classificarIdade(30) //Adulto
    classificarIdade(70) //Idoso
    classificarIdade(-5) // Idade inválida
}