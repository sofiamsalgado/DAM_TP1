package dam.virtual_library

// primary constructor, recebe os valores ao criar o objeto
abstract class Book(
    val title: String, // property, é guardado diretamente no objeto
    val author: String, //property
    year: Int, //parametro simples, não é property
    copies: Int //parametro simples, não é property
) {
    // property com custom getter,
    // year vem do construtor e é transformado aqui, ir de ano a era,
    // transforma o valor ao ler
    val publicationYear: String = when {
        year < 1980 -> "Classic"
        year <= 2010 -> "Modern"
        else -> "Contemporary"
    }

    // property com custom setter, guarda as cópias mas com regras,
    // controla o valor ao escrever
    var availableCopies: Int = copies // valor inicial = copies do construtor
        set(value) {  // set() é chamado sempre que alguém muda o valor
            if (value < 0) return // não aceita negativos
            field = value // guarda o valor de facto
        }

    // init block, corre automaticamente quando o objeto é criado
    init {
        println("Book '$title' by $author has been added to the library.")
    }

    abstract fun getStorageInfo(): String  // metodo abstrato que é definido em cada subclasse

    override fun toString() =  // 6.2 string para representar o objeto
        "Title: $title, Author: $author, Era: $publicationYear, Available: $availableCopies copies"
}