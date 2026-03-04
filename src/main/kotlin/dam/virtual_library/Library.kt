package dam.virtual_library

class Library(val name: String) {
    private val books = mutableListOf<Book>()

    // sem companion object cada library tem o seu proprio contador, com o companion há um único contador
    // partilhado por todas as libraries
    companion object {  //
        private var totalBooks = 0
        fun getTotalBooksCreated() = totalBooks
    }

    fun addBook(book: Book) {
        books.add(book)
        totalBooks++
    }

    fun borrowBook(title: String) {
        val book = books.find { it.title == title }
        if (book == null) {
            println("Book '$title' not found.");
            return
        }
        if (book.availableCopies == 0) {
            println("Sorry, '$title' is out of stock.");
            return
        }
        book.availableCopies--
        println("Successfully borrowed '$title'. Copies remaining: ${book.availableCopies}")
        if (book.availableCopies == 0) println("Warning: Book is now out of stock!")
    }

    fun returnBook(title: String) {
        val book = books.find { it.title == title } // procura na lista o primeiro livro cujo titulo é igual ao title recebido, devolve o livro ou null se não encontrar
        if (book == null) return
        book.availableCopies++
        println("Book '$title' returned successfully. Copies available: ${book.availableCopies}")
    }

    // dar print de todos os livros na library
    fun showBooks() {
        println("\n--- Library Catalog ---")
        books.forEach { println("$it\n") }
    }

    // dar display a todos os livros de um autor especifico
    fun searchByAuthor(author: String) {
        println("Books by $author:")
        books.filter { it.author == author }
            .forEach { println("- ${it.title} (${it.publicationYear}, ${it.availableCopies} copy available)") }
    }
}