package dam.virtual_library

class PhysicalBook(
    title: String, author: String, year: Int, copies: Int,
    val weight: Int, val hasHardcover: Boolean = true // "is set to true by default"
) : Book(title, author, year, copies)