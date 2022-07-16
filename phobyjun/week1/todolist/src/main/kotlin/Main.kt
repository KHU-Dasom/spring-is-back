import todolist.LoginFailedException
import todolist.TaskList
import todolist.User
import todolist.UserSession

fun main() {
    var currentUser: User? = null
    var currentTaskList: TaskList? = null

    while (true) {
        // user authentication
        while (currentUser == null) {
            println("1. 로그인\n2. 유저 등록")
            val inputCommand = input().toInt()

            val userName = input("user name: ")
            val password = input("password: ")
            try {
                when (inputCommand) {
                    1 -> {
                        UserSession.loginUser(userName, password)
                        currentUser = UserSession.currentUser
                    }
                    2 -> {
                        UserSession.newUser(userName, password)
                    }
                }
            } catch (e: LoginFailedException) {
                println(e.message)
            }
        }
    }
}

fun input(message: String = ""): String {
    print("> $message")
    return readLine()!!
}