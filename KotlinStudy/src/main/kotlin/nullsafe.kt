data class Text(
    var text: String = "hello"
)

fun main(args: Array<String>) {
    var message: Text? = Text("Hello Hello")
    var nullMessage: Text? = null

    message?.let{
        println("message : ${it.text}")
    }

    nullMessage?.let{
        println("message 출력 안됨")
    }
}