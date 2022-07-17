data class Text(
    var text: String = "hello"
)

/*
class MM : Serialize{ // 자바에서 serial
}
 */

fun main(args: Array<String>) {
    var message: Text? = Text("Hello Hello")
    var nullMessage: Text? = null

    var m = if (message == null){
        5
    } else{
        0
    }
}