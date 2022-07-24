data class Text(
    var text: String = "hello"
)

fun getName(str: String?): String{
    val name = str ?: "Unknown"
    return name
}
fun main(args: Array<String>) {
    println(getName(null))
    println(getName("non-null"))
}