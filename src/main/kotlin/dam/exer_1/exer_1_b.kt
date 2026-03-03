package org.example.dam.exer_1
/*
    b) Using a range and map()
*/

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val squares = (1..50).map {it*it}
    println(squares)
}