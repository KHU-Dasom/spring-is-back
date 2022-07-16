package todolist

import todolist.todo.Task
import java.time.LocalDate

class TaskList {
    private var taskList = mutableListOf<Task>()

    fun printTasks() {
        println("=============== 할 일 목록 ===============")
        println(
            "No.".padEnd(4) +
                    "Task".padEnd(20) +
                    "Due".padEnd(15) +
                    "Status"
        )
        for ((idx, task) in taskList.withIndex()) {
            println(
                "${idx + 1}".padEnd(4) +
                        task.text.padEnd(20) +
                        "${task.dueDate}".padEnd(15) +
                        "${task.status}"
            )
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
