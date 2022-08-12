package kr.frec.week1.todoManager

import java.time.LocalDateTime

data class Todo (
    val shortDesc: String,
    val longDesc: String,
    val deadline: LocalDateTime? = null,
    var isFinished: Boolean = false
) {
    fun setFinish() { this.isFinished = true }
}