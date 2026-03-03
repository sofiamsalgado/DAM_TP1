package org.example.dam.exer_2
/*
    Create a console-based calculator that allows users to perform various operations.
*/


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println("=== Calculadora ===")
    println("Operacoes: +  -  *  /  &&  ||  !  shl  shr")

    print("Introduz o primeiro numero: ")
    val a = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("Numero invalido")

    print("Introduz a operacao: ")
    val op = readLine()?.trim() ?: throw IllegalArgumentException("Operacao invalida")

    val resultado: Int

    if (op == "!") {
        resultado = if (a == 0) 1 else 0
    } else {
        print("Introduz o segundo numero: ")
        val b = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("Numero invalido")

        resultado = when (op) {
            "+"   -> a + b
            "-"   -> a - b
            "*"   -> a * b
            "/"   -> {
                if (b == 0) throw ArithmeticException("Divisao por zero")
                a / b
            }
            "&&"  -> if (a != 0 && b != 0) 1 else 0
            "||"  -> if (a != 0 || b != 0) 1 else 0
            "shl" -> a shl b
            "shr" -> a shr b
            else  -> throw IllegalArgumentException("Operacao desconhecida: $op")
        }
    }

    println("\n=== Resultado ===")
    println("Decimal:      $resultado")
    println("Hexadecimal:  0x${resultado.toString(16).uppercase()}")
    println("Booleano:     ${resultado != 0}")
}