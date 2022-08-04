package io.dasom.springreview

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringReviewApplication

fun main(args: Array<String>) {
    runApplication<SpringReviewApplication>(*args)
}
