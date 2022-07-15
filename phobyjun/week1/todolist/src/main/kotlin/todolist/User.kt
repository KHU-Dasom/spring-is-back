package todolist

class User(name: String, password: String) {
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
}

fun newUser(name: String, password: String): User {
    return User(name, password)
}