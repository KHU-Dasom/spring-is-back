package todolist.todo

import java.util.*

data class Task(
    val text: String,
    val dueDate: Date?,
    val status: TaskStatus,
) {
    constructor(text: String) : this(text, null, TaskStatus.READY) {
    }
}
