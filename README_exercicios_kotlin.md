# Assignment 1 — Exercicios Kotlin (Exercicio 1, 2 e 3)

**Curso:** Licenciatura em Engenharia Informatica e Multimedia
**Unidade Curricular:** Desenvolvimento de Aplicacoes Moveis (DAM)
**Aluno:** Sofia — dam_a51694
**Data:** Fevereiro/Marco 2026
**Repositorio:** _[URL do repositorio GitHub]_

---

## 1. Introducao

O objetivo desta parte do trabalho foi aprender os conceitos basicos de Kotlin sem o uso de ferramentas de IA ou auto-complete. Foram realizados tres exercicios focados em tipos basicos, controlo de fluxo e sequencias funcionais.

---

## 2. Descricao do Sistema

Tres programas de consola independentes:

- **Exercicio 1:** Criacao de arrays com os primeiros 50 quadrados perfeitos, utilizando tres metodos diferentes.
- **Exercicio 2:** Calculadora de consola com operacoes aritmeticas, booleanas e bitwise.
- **Exercicio 3:** Simulacao de saltos de uma bola utilizando sequencias funcionais (`generateSequence`).

---

## 3. Arquitetura e Design

Projeto criado no IntelliJ IDEA com Maven e repositorio Git local.

Estrutura de pastas:
```
DAM_TP1/
├── src/
│   └── org/example/dam/
│       ├── exer_1/exer_1_a.kt
│       ├── exer_1/exer_1_b.kt
│       ├── exer_1/exer_1_c.kt
│       ├── exer_2/exer_2.kt
│       └── exer_3/exer_3.kt
└── pom.xml
```

Cada exercicio esta num ficheiro separado dentro do seu proprio package, conforme indicado no enunciado.

---

## 4. Implementacao

### Exercicio 1 — Arrays de quadrados perfeitos

Foram usados tres metodos para criar um array com os primeiros 50 quadrados perfeitos (1, 4, 9, ..., 2500):

**a) IntArray constructor:**
```kotlin
val squares = IntArray(50) { (it + 1) * (it + 1) }
println(squares.joinToString())
```
O parametro `it` representa o indice atual (0 a 49). Soma-se 1 para obter os numeros de 1 a 50 e eleva-se ao quadrado.

**b) Range e map():**
```kotlin
val squares = (1..50).map { it * it }
println(squares)
```
Cria um range de 1 a 50 e aplica `map` para elevar cada valor ao quadrado. Devolve uma `List<Int>`.

**c) Array com constructor:**
```kotlin
val squares = Array(50) { (it + 1) * (it + 1) }
println(squares.joinToString())
```
Semelhante ao metodo a) mas devolve um `Array<Int>` em vez de `IntArray`. Usa `joinToString()` para imprimir os valores separados por virgula.

---

### Exercicio 2 — Calculadora de consola

A calculadora le um ou dois numeros inteiros e uma operacao do utilizador. Usa `when` para tratar cada operacao e lanca excecoes para erros como divisao por zero ou entrada invalida.

```kotlin
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
```

O operador `!` e tratado separadamente pois so precisa de um numero. O resultado e mostrado em decimal, hexadecimal e booleano.

Exemplo de saida para `12 / 4`:
```
=== Resultado ===
Decimal:      3
Hexadecimal:  0x3
Booleano:     true
```

---

### Exercicio 3 — Simulacao de saltos

Uma bola cai de 100 metros. A cada salto atinge 60% da altura anterior.

```kotlin
fun main() {
    val bounces = generateSequence(100.0) { it * 0.6 }
        .takeWhile { it >= 1.0 }
        .take(15)
        .map { Math.round(it * 100) / 100.0 }
        .toList()

    println(bounces)
}
```

- `generateSequence(100.0) { it * 0.6 }` — começa em 100.0 e multiplica por 0.6 a cada iteracao.
- `takeWhile { it >= 1.0 }` — para quando a altura desce abaixo de 1 metro.
- `take(15)` — garante no maximo 15 saltos.
- `map { Math.round(it * 100) / 100.0 }` — arredonda cada valor a 2 casas decimais.

---

## 5. Testes e Validacao

- Exercicio 1: verificado que os tres metodos produzem os mesmos valores (1, 4, 9, 16, ..., 2500).
- Exercicio 2: testadas divisoes por zero (lanca `ArithmeticException`), entradas invalidas (lanca `IllegalArgumentException`) e todas as operacoes disponiveis.
- Exercicio 3: verificado que todos os valores impressos sao maiores ou iguais a 1.0 e seguem a progressao de 60%.

---

## 6. Instrucoes de Uso

1. Abrir o projeto `DAM_TP1` no IntelliJ IDEA.
2. Navegar ate ao ficheiro do exercicio pretendido.
3. Clicar com o botao direito no ficheiro e escolher **Run**.
4. Para o exercicio 2, introduzir os valores pedidos na consola quando solicitado.

---

## 12. Controlo de Versao

O repositorio foi criado localmente no IntelliJ com Git. O mesmo repositorio contem os exercicios Kotlin (secao 2) e a Biblioteca Virtual (secao 6). Os commits dos exercicios sao:

- `Initial commit`
- `Exercicios 1, 2 e 3`

---

## 13. Dificuldades e Licoes Aprendidas

- No exercicio 2, o operador `!` so precisa de um numero — foi necessario tratar esse caso separadamente antes do `when` para evitar pedir o segundo numero desnecessariamente.
- O exercicio 3 exigiu perceber como `generateSequence` funciona. Usou-se `takeWhile` em vez de `filter` porque `takeWhile` para assim que a condicao falha, o que e mais eficiente para uma sequencia decrescente.
- Aprender a nao usar IA nestes exercicios forcou a leitura da documentacao oficial do Kotlin, o que foi util para consolidar os conceitos.

---

## 14. Melhorias Futuras

- Exercicio 2: adicionar suporte a numeros decimais (`Double`).
- Exercicio 2: adicionar um ciclo para permitir varias operacoes sem reiniciar o programa.
- Exercicio 3: permitir ao utilizador definir a altura inicial e a percentagem de ricochete como argumentos.

---

## 15. Declaracao de Uso de IA (Obrigatorio)

Estes exercicios foram realizados **sem uso de IA nem auto-complete**, conforme indicado no enunciado (AC NO, AI NO).

A IA (Claude) foi consultada pontualmente apenas para:
- Resolver erros e bugs encontrados durante o desenvolvimento
- Consultar exemplos de utilizacao de `generateSequence`, `map`, `takeWhile` e tratamento de excecoes com `throw`

Todo o restante codigo foi escrito manualmente com base na documentacao oficial do Kotlin (https://kotlinlang.org/docs/home.html) e nos conceitos abordados nos slides e documentos fornecidos pelos professores.
