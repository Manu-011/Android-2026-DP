class Carro(
    //Constructores
    val placa: String,
    val ano: Int,
    val modelo : String,
    val motorista : String
)  {
    // metodos
    fun moverParaFrente() {
        println("O carro $modelo (placa: $placa), dirigido por $motorista, está se movendo para FRENTE.")
    }
    fun moverParaTras() {
        println("o carro $modelo (placa: $placa), dirigido por $motorista, esta se movendo para TRAS")
    }

    fun fazerBalizaDireita() {
        println("O carro $modelo (placa: $placa), dirigido por $motorista, está fazendo baliza para a DIREITA.")
    }

    fun fazerBalizaEsquerda() {
        println("O carro $modelo (placa: $placa), dirigido por $motorista, está fazendo baliza para a ESQUERDA.")
    }


}
//Funcao principal para rodar o programa
fun main() {
    val carro1 = Carro(placa = "ABC-1234", ano = 2020, modelo = "Civic", motorista = "Ana")
    val carro2 = Carro(placa = "XYZ-9876", ano = 2022, modelo = "Corolla", motorista = "Carlos")
    val carro3 = Carro(placa = "KOT-2024", ano = 2023, modelo = "Onix", motorista = "Beatriz")

println("--- Iniciando os movimentos ---\n")

carro1.moverParaFrente()
carro2.fazerBalizaDireita()
carro3.fazerBalizaEsquerda()
}