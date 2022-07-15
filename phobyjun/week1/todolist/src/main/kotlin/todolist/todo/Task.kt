package todolist.todo

import java.time.LocalDate

data class Task(
    val text: String,
    val dueDate: LocalDate?,
    val status: TaskStatus,
) {
    constructor(text: String) : this(text, null, TaskStatus.READY) {
    }
}
