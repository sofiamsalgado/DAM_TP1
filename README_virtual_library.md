# Assignment 1 — Biblioteca Virtual (Virtual Library)

**Curso:** Licenciatura em Engenharia Informatica e Multimedia
**Unidade Curricular:** Desenvolvimento de Aplicacoes Moveis (DAM)
**Aluno:** Sofia — dam_a51694
**Data:** Fevereiro/Marco 2026
**Repositorio:** https://github.com/sofiamsalgado/DAM_TP1

---

## 1. Introducao

O objetivo foi implementar um sistema de gestao de biblioteca virtual em Kotlin, aplicando conceitos de programacao orientada a objetos: heranca, polimorfismo, getters/setters personalizados, metodos abstratos, companion objects e data classes. Esta tarefa foi realizada sem IA nem auto-complete.

---

## 2. Descricao do Sistema

Sistema de consola que gere uma colecao de livros (digitais e fisicos). Permite adicionar livros, fazer emprestimos, devolver e pesquisar por autor.

---

## 3. Arquitetura e Design

```
DAM_TP1/
└── src/dam/virtual_library/
    ├── Book.kt
    ├── DigitalBook.kt
    ├── PhysicalBook.kt
    ├── Library.kt
    ├── LibraryMember.kt
    └── Main.kt
```

Hierarquia de classes:
```
Book (abstract)
├── DigitalBook
└── PhysicalBook
Library (com companion object)
LibraryMember (data class)
```

---

## 4. Implementacao

### Passo a passo

1. Criar o package `dam.virtual_library` no projeto `DAM_TP1`.
2. Implementar a classe base abstrata `Book`.
3. Implementar as subclasses `DigitalBook` e `PhysicalBook`.
4. Implementar a classe `Library` com companion object.
5. Implementar a data class `LibraryMember`.
6. Testar tudo no `Main.kt`.

### Classe Book (base abstrata)

```kotlin
abstract class Book(
    val title: String,
    val author: String,
    private val year: Int,
    availableCopies: Int
) {
    init {
        println("Book '$title' by $author has been added to the library.")
    }

    val publicationYear: String
        get() = when {
            year < 1980 -> "Classic"
            year <= 2010 -> "Modern"
            else -> "Contemporary"
        }

    var availableCopies: Int = availableCopies
        set(value) {
            if (value < 0) return
            if (value == 0) println("Warning: Book is now out of stock!")
            field = value
        }

    abstract fun getStorageInfo(): String

    override fun toString(): String =
        "Title: $title, Author: $author, Era: $publicationYear, Available: $availableCopies copies"
}
```

### DigitalBook

```kotlin
class DigitalBook(
    title: String, author: String, year: Int, copies: Int,
    val fileSize: Double, val format: String
) : Book(title, author, year, copies) {
    override fun getStorageInfo() = "Stored digitally: $fileSize MB, Format: $format"
}
```

### PhysicalBook

```kotlin
class PhysicalBook(
    title: String, author: String, year: Int, copies: Int,
    val weight: Int, val hasHardcover: Boolean = true
) : Book(title, author, year, copies) {
    override fun getStorageInfo() = "Physical book: ${weight}g, Hardcover: ${if (hasHardcover) "Yes" else "No"}"
}
```

### Library com companion object

```kotlin
class Library(val name: String) {
    private val books = mutableListOf<Book>()

    companion object {
        private var totalBooks = 0
        fun getTotalBooksCreated() = totalBooks
    }

    fun addBook(book: Book) { books.add(book); totalBooks++ }

    fun borrowBook(title: String) {
        val book = books.find { it.title == title }
        if (book == null) { println("Book not found."); return }
        if (book.availableCopies == 0) { println("Sorry, '$title' is out of stock."); return }
        book.availableCopies--
        println("Successfully borrowed '$title'. Copies remaining: ${book.availableCopies}")
    }

    fun returnBook(title: String) {
        val book = books.find { it.title == title } ?: return
        book.availableCopies++
        println("Book '$title' returned successfully. Copies available: ${book.availableCopies}")
    }

    fun showBooks() {
        println("--- Library Catalog ---")
        books.forEach { println("$it\nStorage: ${it.getStorageInfo()}") }
    }

    fun searchByAuthor(author: String) {
        println("Books by $author:")
        books.filter { it.author == author }
            .forEach { println("- ${it.title} (${it.publicationYear}, ${it.availableCopies} copy available)") }
    }
}
```

### LibraryMember (data class)

```kotlin
data class LibraryMember(
    val name: String,
    val membershipId: String,
    val borrowedBooks: MutableList<String> = mutableListOf()
)
```

---

### 6.5 — Assessment Criteria

Na fase 6.5 foram adicionados testes ao `Main.kt` para verificar cada criterio de avaliacao. O codigo completo do `main()` e o seguinte:

```kotlin
fun main() {
    val library = Library("Central Library")
    val digitalBook = DigitalBook("Kotlin in Action", "Dmitry Jemerov", 2017, 5, 4.5, "PDF")
    val physicalBook = PhysicalBook("Clean Code", "Robert C. Martin", 2008, 3, 650, true)
    val classicBook = PhysicalBook("1984", "George Orwell", 1949, 2, 400, false)

    library.addBook(digitalBook)
    library.addBook(physicalBook)
    library.addBook(classicBook)
    library.showBooks()

    println("--- Borrowing Books---")
    library.borrowBook("Clean Code")
    library.borrowBook("1984")
    library.borrowBook("1984")
    library.borrowBook("1984") // Should fail - no copies left

    println("\n--- Returning Books---")
    library.returnBook("1984")

    println("\n--- Search by Author---")
    library.searchByAuthor("George Orwell")

    println("\n--- 6.5 Assessment Criteria---")

    // === custom getter ===
    println("\n--- Publication Year (custom getter) ---")
    println("2017 -> ${digitalBook.publicationYear}")
    println("2008 -> ${physicalBook.publicationYear}")
    println("1949 -> ${classicBook.publicationYear}")

    // === custom setter (valor negativo) ===
    println("\n--- Custom Setter (valor negativo) ---")
    classicBook.availableCopies = -5
    println("Copias apos tentar -5: ${classicBook.availableCopies}")

    // === livro inexistente ===
    println("\n--- Livro inexistente ---")
    library.returnBook("Livro Inexistente")

    // === companion object ===
    println("\n--- Companion Object ---")
    println("Total livros criados: ${Library.getTotalBooksCreated()}")

    // === data class ===
    println("\n--- LibraryMember (data class) ---")
    val member = LibraryMember("Sofia", "ID123")
    member.borrowedBooks.add("1984")
    member.borrowedBooks.add("Clean Code")
    println(member)
    val member2 = member.copy(name = "Daniel", membershipId = "ID456")
    println(member2)
}
```

---

O que cada teste verifica, por ordem dos criterios de avaliacao:

**1. Declaracao correta de classes e objetos**
Os tres livros sao criados com os seus construtores primarios — `DigitalBook` e `PhysicalBook` herdam de `Book` e passam os parametros comuns para o construtor pai com `super`. A `Library` e instanciada com o nome `"Central Library"`.

**2. Uso correto de construtores primarios e secundarios**
Todos os objetos sao criados com construtores primarios. O `PhysicalBook` tem o parametro `hasHardcover` com valor por omissao (`true`), demonstrando parametros opcionais no construtor.

**3. Uso do bloco init**
O bloco `init` da classe `Book` e executado automaticamente ao criar cada livro, imprimindo a mensagem de confirmacao. Isto e visivel no output ao chamar `addBook()` — a mensagem aparece antes de qualquer outra acao.

**4. Getter e setter personalizados**
O getter de `publicationYear` e testado com os tres livros — devolve `"Contemporary"` para 2017, `"Modern"` para 2008 e `"Classic"` para 1949. O setter de `availableCopies` e testado com valor negativo (`-5`) — o valor nao e alterado, comprovando que a validacao funciona.

**5. Heranca e subclasses**
`DigitalBook` e `PhysicalBook` herdam de `Book`. Cada uma tem propriedades proprias (`fileSize`, `format` e `weight`, `hasHardcover`) e implementa o metodo abstrato `getStorageInfo()` de forma diferente, demonstrando polimorfismo.

**6. Funcionalidade da classe Library**
Sao testados todos os metodos: `addBook()`, `showBooks()`, `borrowBook()`, `returnBook()` e `searchByAuthor()`.

**7. Organizacao e legibilidade do codigo**
Cada classe esta no seu proprio ficheiro dentro do package `dam.virtual_library`. Os metodos sao curtos e com nomes descritivos.

**8. Tratamento de casos limite**
Sao testados dois casos limite: tentar emprestar `"1984"` quando ja nao ha copias (falha com mensagem), e tentar devolver `"Livro Inexistente"` que nao existe na biblioteca.

**9. Metodos abstratos, companion objects e data classes**
- O metodo abstrato `getStorageInfo()` e chamado indiretamente via `showBooks()` para todos os livros.
- O companion object e acedido diretamente pela classe: `Library.getTotalBooksCreated()` — sem precisar de uma instancia de `Library`.
- A data class `LibraryMember` e testada criando dois membros, adicionando livros emprestados, usando `println()` (que chama o `toString()` automatico da data class) e usando `copy()` para criar um segundo membro baseado no primeiro alterando apenas o nome e o ID.

---

## 5. Testes e Validacao

- Testado com o exemplo do enunciado: emprestar livros disponiveis, tentar emprestar sem copias, devolver e pesquisar por autor.
- Verificado que o setter de `availableCopies` impede valores negativos e imprime aviso ao chegar a zero.
- Verificado o output esperado em consola.

---

## 6. Instrucoes de Uso

1. Abrir o projeto `DAM_TP1` no IntelliJ IDEA.
2. Navegar ate `dam/virtual_library/Main.kt`.
3. Clicar com o botao direito e escolher **Run**.
4. Observar o output na consola.

---

## 12. Controlo de Versao

O mesmo repositorio contem os exercicios Kotlin (secao 2) e a Biblioteca Virtual (secao 6). Os commits da Biblioteca Virtual sao:

- `Initial commit`
- `Exercicios 1, 2 e 3`
- `6. 1 class Book, classe base`
- `6.1 Subclasses DigitalBook e PhysicalBook`
- `6.1 Class library`
- `6.2 Extended requirements, toString() metodo, metodo abstrato getStorageInfo, companion object and data classe`
- `6.3 Expected Implementation, Main`
- `6.4 Expected Console Output, ajustes na classe Book e Library`
- `6.5 Assessment Criteria, fazer mais testes e utilizar metodos abstratos, companion objects e data classes`

---

## 13. Dificuldades e Licoes Aprendidas

- Implementar o getter personalizado para `publicationYear` com logica de negocio dentro da propriedade foi um conceito novo mas muito util.
- O setter com validacao de `availableCopies` obrigou a perceber bem a diferenca entre `field` e a propriedade em si no Kotlin.
- O companion object foi util para perceber como funciona o equivalente a membros estaticos em Kotlin.

---

## 14. Melhorias Futuras

- Persistir os dados em ficheiro para nao perder o estado ao fechar o programa.
- Adicionar interface grafica com JavaFX ou migrar para Android.
- Implementar sistema de membros com historico de emprestimos.

---

## 15. Declaracao de Uso de IA (Obrigatorio)

Esta tarefa foi realizada **sem uso de IA nem auto-complete** (AC NO, AI NO).

A IA (Claude) foi consultada pontualmente apenas para:
- Resolver erros e bugs encontrados durante o desenvolvimento
- Consultar exemplos de como declarar classes abstratas e metodos abstratos em Kotlin
- Consultar exemplos de utilizacao de companion objects e como funcionam em Kotlin
- Consultar exemplos de data classes e quando devem ser usadas
- Consultar a sintaxe de getters e setters personalizados com a palavra-chave `field`

Todo o restante codigo foi escrito manualmente com base na documentacao oficial do Kotlin (https://kotlinlang.org/docs/home.html) e nos conceitos de orientacao a objetos abordados nos slides fornecidos pelos professores.
