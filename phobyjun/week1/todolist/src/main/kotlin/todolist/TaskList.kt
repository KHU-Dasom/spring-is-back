package todolist

import todolist.todo.Task
import java.time.LocalDate

class TaskList {
    private var taskList = mutableListOf<Task>()

    fun printTasks() {
        println("=============== 할 일 목록 ===============")
        println("번호\t내용\t기한\t상태")
        for ((idx, task) in taskList.withIndex()) {
            println("$idx\t${task.text}\t${task.dueDate}\t${task.status}")
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
