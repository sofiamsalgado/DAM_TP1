package org.example.dam.exer_3
/*
    A ball is dropped from a height of 100 meters. Each time it bounces, it reaches 60% of its
    previous height. Write a program using generateSequence to model the bounce heights,
    filter to keep only bounces that reach at least 1 meter high, take the first 15 qualifying
    bounces, convert to a list, and print the heights rounded to 2 decimal places. Print the
    sequence
*/

/*
    generateSequence retorna uma sequencia que invoca a função que calcula o próximo valor em cada iteração
    até a função dar null
 */

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val bounces = generateSequence(100.0) {it * 0.6}
        .takeWhile{it >= 1.0}
        .take(15)
        .map{Math.round(it * 100)/100.0}
        .toList()

    println(bounces)
}