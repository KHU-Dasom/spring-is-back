
fun describeString(maybeString: String?): String? {              // 1
    if (maybeString != null && maybeString.length > 0) {        // 2
        return "String of length ${maybeString.length}"
    } else {
        return null                           // 3
    }
}
fun main() {
    var nullable: String? = "You can keep a null here"
    println(describeString(null))
}