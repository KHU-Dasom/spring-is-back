class MyService{
    fun performAction(): String = "foo"
}

class MyTest {
    private lateinit var myService: MyService

    fun setUp() {
        myService = MyService()
    }

    fun testAction() {
        println(myService.performAction())
    }
}
fun main(){
    var test : MyTest = MyTest()
    test.setUp()
    test.testAction()
}