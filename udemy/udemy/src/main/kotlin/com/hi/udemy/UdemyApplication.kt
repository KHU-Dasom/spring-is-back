package com.hi.udemy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UdemyApplication

fun main(args: Array<String>) {
	runApplication<UdemyApplication>(*args)
}
