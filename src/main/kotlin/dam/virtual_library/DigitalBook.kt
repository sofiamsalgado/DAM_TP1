package dam.virtual_library

class DigitalBook(
    title: String, author: String, year: Int, copies: Int,
    val fileSize: Double, val format: String
) : Book(title, author, year, copies)