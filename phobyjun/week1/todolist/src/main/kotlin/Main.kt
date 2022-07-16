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
            println("1. 로그인\t2. 유저 등록")
            val inputCommand = input().toInt()

            val userName = input("이름: ")
            val password = input("비밀번호: ")
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

        // todolist application
        while (currentUser != null) {
            while (currentTaskList == null) {
                currentUser?.printCurrentTaskList()

                print("TodoList를 선택해주세요.\nTodoList 목록 | ")
                currentUser!!.printTaskLists()

                println("1. TodoList 생성\t2.TodoList 삭제\t3. TodoList 선택\t4. 로그아웃")
                when (input().toInt()) {
                    1 -> currentUser.newTaskList(input("TodoList 이름: "))
                    2 -> currentUser.deleteTaskList(input("TodoList 이름: "))
                    3 -> currentTaskList = currentUser
                        .selectTaskList(input("선택할 TodoList 이름: "))
                    4 -> {
                        currentUser = null
                        currentTaskList = null
                        break
                    }
                }
            }
            while (currentTaskList != null) {
                currentUser?.printCurrentTaskList()

                currentTaskList.printTasks()
                println("1. Task 생성\t2. 일정 Task 생성\t3. Task 삭제\t4. TodoList 변경\t5. 로그아웃")
                println("6. Task 상태 변경\t7. Task 정렬 변경")
                when (input().toInt()) {
                    1 -> currentTaskList.newTask(input("할 일: "))
                    2 -> currentTaskList.newTaskWithDueDate(input("할 일: "), input("기한: "))
                    3 -> currentTaskList.deleteTask(input("번호: ").toInt() - 1)
                    4 -> currentTaskList = null
                    5 -> {
                        currentUser = null
                        currentTaskList = null
                    }
                    6 -> {
                        println("상태 변경(e.g. READY | DOING | DONE)")
                        currentTaskList.changeStatus(input("번호: ").toInt() - 1, input("상태: "))
                    }
                }
            }
        }
    }
}

fun input(message: String = ""): String {
    print("> $message")
    return readLine()!!
}