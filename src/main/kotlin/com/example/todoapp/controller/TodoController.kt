package com.example.todoapp.controller

import com.example.todoapp.model.CreateTaskBody
import com.example.todoapp.model.Task
import com.example.todoapp.service.TodoServiceInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TodoController {
    @Autowired
    lateinit var todoService: TodoServiceInterface

    @GetMapping
    fun getTaskList(): ArrayList<Task> {
        return todoService.getTodoList()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTask(@RequestBody requestBody: CreateTaskBody): Task {
        return todoService.createTodo(requestBody.title)
    }
}
