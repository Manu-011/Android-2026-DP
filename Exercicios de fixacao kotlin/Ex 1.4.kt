fun fatorial(n: Int): Int {
    return if (n == 1) {
        1
    } else {
        n * fatorial(n-1)
    }
}

fun main() {
    val numero = 5
    println ("O fatorial de $numero e: ${fatorial(numero)}")
}