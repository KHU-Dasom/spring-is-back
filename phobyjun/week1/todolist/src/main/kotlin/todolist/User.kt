package todolist

class User(val name: String, private val password: String) {
    private var taskLists = mutableMapOf<String, TaskList>()
    private var selectedTaskList: String? = null

    fun printTaskLists() {
        taskLists.forEach { print("${it.key}\t") }
        println()
    }

    fun printCurrentTaskList() {
        println(
            "현재 선택된 TodoList: ${
                if (selectedTaskList == null) {
                    "없음"
                } else {
                    selectedTaskList
                }
            }"
        )
    }

    fun newTaskList(taskListName: String) {
        taskLists[taskListName] = TaskList()
    }

    fun deleteTaskList(taskListName: String) {
        taskLists.remove("taskListName")
    }

    fun selectTaskList(name: String): TaskList? {
        selectedTaskList = name
        return taskLists[selectedTaskList]
    }

    fun checkPassword(password: String): Boolean {
        return password == this.password
    }
}
