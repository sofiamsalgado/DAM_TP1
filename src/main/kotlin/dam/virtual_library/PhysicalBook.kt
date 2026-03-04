package dam.virtual_library

class PhysicalBook(
    title: String, author: String, year: Int, copies: Int,
    val weight: Int, val hasHardcover: Boolean = true // "is set to true by default" -> enunciado
) : Book(title, author, year, copies){

    override fun getStorageInfo() = "Physical book: ${weight}g, Hardcover: ${if (hasHardcover) "Yes" else "No"}"

    override fun toString() = "${super.toString()}\nStorage: ${getStorageInfo()}"
}