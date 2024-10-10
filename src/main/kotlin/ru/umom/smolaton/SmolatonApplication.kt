package ru.umom.smolaton

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class SmolatonApplication

fun main(args: Array<String>) {
    runApplication<SmolatonApplication>(*args)

}

