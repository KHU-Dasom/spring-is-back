package kr.frec.week1.todoManager

import java.time.LocalDateTime

class TodoManager {
    private var todos = ArrayList<Todo>()

    fun size(): Int {
        return this.todos.size
    }

    fun inProgressItems(): ArrayList<Todo> {
        return ArrayList<Todo>(
            this.todos
                .filterNot { it.isFinished }
                .toList())
    }

    fun finishedItems(): ArrayList<Todo> {
        return ArrayList<Todo>(
            this.todos
                .filterNot { it.isFinished }
                .toList())
    }

    fun allItems(): ArrayList<Todo> { return this.todos }

    fun addTodo(short: String, long: String, deadline: LocalDateTime?) {
        this.todos.add(Todo(
            shortDesc = short,
            longDesc = long,
            deadline = deadline,
        ))
    }
}