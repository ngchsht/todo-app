package com.example.todoapp.controller

import com.example.todoapp.model.Task
import com.example.todoapp.service.TodoServiceInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TodoController {
    @GetMapping("/")
    fun test(): String {
        return "hello, world.";
    }

    @Autowired
    lateinit var todoService: TodoServiceInterface

    @GetMapping("/tasks")
    fun getTaskList(): ArrayList<Task> {
        return todoService.getTodoList()
    }
}
