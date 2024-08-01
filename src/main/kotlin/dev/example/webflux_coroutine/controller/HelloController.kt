package dev.example.webflux_coroutine.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/")
    fun index(): String {
        return "main page"
    }

    @GetMapping("/hello")
    suspend fun hello(): String {
        return "Hello world"
    }

}