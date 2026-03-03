package org.example.dam.exer_1
/*
    a) Using IntArray construtor
*/

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val squares = IntArray(50) { (it + 1) * (it + 1)}
    println(squares.joinToString())
}