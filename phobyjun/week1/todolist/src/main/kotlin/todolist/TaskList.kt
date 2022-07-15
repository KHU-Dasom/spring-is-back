package todolist

import todolist.todo.Task
import java.time.LocalDate

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

    fun newTaskWithDueDate(text: String, dueDate: String) {
        val taskWithDate: (String, String) -> Task = { t, d -> Task(t, LocalDate.parse(d)) }
        this.taskList.add(taskWithDate(text, dueDate))
    }
}
