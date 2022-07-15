package todolist

import todolist.todo.Task

class TaskList {
    private var taskList = mutableListOf<Task>()

    fun printTasks() {
        for (task in this.taskList) {
            print(task)
        }
    }

    fun newTask(text: String) {
        val task = Task(text)
        this.taskList.add(task)
    }
}
