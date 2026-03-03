package org.example.dam.exer_1
/*
    c) Using Array with constructor
*/

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val squares = Array(50) {(it+1) * (it + 1)}
    println(squares.joinToString())
}