package dam.virtual_library

fun main() {
    val library = Library("Central Library")
    val digitalBook = DigitalBook(
        "Kotlin in Action",
        "Dmitry Jemerov",
        2017,
        5,
        4.5,
        "PDF"
    )
    val physicalBook = PhysicalBook(
        "Clean Code",
        "Robert C. Martin",
        2008,
        3,
        650,
        true
    )
    val classicBook = PhysicalBook(
        "1984",
        "George Orwell",
        1949,
        2,
        400,
        false
    )
    library.addBook(digitalBook)
    library.addBook(physicalBook)
    library.addBook(classicBook)
    library.showBooks()
    println("--- Borrowing Books---")
    library.borrowBook("Clean Code")
    library.borrowBook("1984")
    library.borrowBook("1984")
    library.borrowBook("1984") // Should fail- no copies left

    println("\n--- Returning Books---")
    library.returnBook("1984")

    println("\n--- Search by Author---")
    library.searchByAuthor("George Orwell")

    //---------------------------------------//----------------------------------------

    println("\n--- 6.5 Assessment Criteria---")

    // === custom getter ===
    println("\n--- Publication Year (custom getter) ---")
    println("2017 -> ${digitalBook.publicationYear}")
    println("2008 -> ${physicalBook.publicationYear}")
    println("1949 -> ${classicBook.publicationYear}")

    // === custom setter (valor negativo) ===
    println("\n--- Custom Setter (valor negativo) ---")
    classicBook.availableCopies = -5
    println("Cópias após tentar -5: ${classicBook.availableCopies}")

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