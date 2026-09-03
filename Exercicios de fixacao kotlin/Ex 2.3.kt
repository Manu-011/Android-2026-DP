fun fatorial(n:Int): Long {
    var resultado = 1L
    for (i in 1..n) {
        resultado *= i
    }
    return resultado
}
fun main () {
    println("Fatorial de 5: ${fatorial(5)}") //120
    println("Fatorial de 10: ${fatorial (10)}") // 3628800
}