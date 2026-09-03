fun isPar(numero: Int) : Boolean {
    return numero % 2 == 0
}

fun main (){
    val numero = 4
    println("O número $numero é par? ${isPar(numero)}")
}