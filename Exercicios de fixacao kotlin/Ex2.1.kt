fun maiorDeDois(a: Int, b: Int) {
    if (a > b) {
        println("O maior numero e: $a")
    } else if (b > a) {
        println("O maior numero e: $b")
    } else {
        println ("Os dois numeros sao iguais ($a).")
    }

}
fun main (){
    //Exemplo de dois
    maiorDeDois(10, 5)
    maiorDeDois(3, 8)
    maiorDeDois(4, 4)
}