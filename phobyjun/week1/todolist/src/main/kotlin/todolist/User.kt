package todolist

class User(val name: String, private val password: String) {
    private var taskLists = mutableMapOf<String, TaskList>()
    private var selectedTaskList: String = ""

    fun printTaskLists() {
        taskLists.forEach { println(it) }
    }

    fun printCurrentTaskList() {
        taskLists[selectedTaskList]?.printTasks()
    }

    fun newTaskList(taskListName: String) {
        taskLists[taskListName] = TaskList()
    }

    fun selectTaskList(name: String): TaskList? {
        selectedTaskList = name
        return taskLists[selectedTaskList]
    }

    fun checkPassword(password: String): Boolean {
        return password == this.password
    }
}
