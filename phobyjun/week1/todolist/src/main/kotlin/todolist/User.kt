package todolist

class User(val name: String, val password: String) {
    private var taskLists = mutableMapOf<String, TaskList>()
    private var selectedTaskLists: String = ""

    fun printTodoList() {
        taskLists.forEach { println(it) }
    }

    fun printCurrentTodoList() {
        taskLists[selectedTaskLists]?.printTasks()
    }

    fun newTaskList(taskListName: String) {
        taskLists[taskListName] = TaskList()
    }

    fun selectTaskList() {}// TODO: 2022/07/15

    fun checkPassword(password: String): Boolean {
        return password == this.password
    }
}

fun newUser(name: String, password: String): User {
    return User(name, password)
}