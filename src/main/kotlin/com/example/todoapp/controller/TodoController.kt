package com.example.todoapp.controller

import com.example.todoapp.model.CreateTaskBody
import com.example.todoapp.model.Task
import com.example.todoapp.model.UpdateTaskBody
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

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateTask(@PathVariable("id") id: Int, @RequestBody requestBody: UpdateTaskBody) {
        val succeeded = todoService.updateTodo(id, requestBody.completed)
        //TODO: 異常系
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTask(@PathVariable("id") id: Int) {
        val succeeded = todoService.deleteTodo(id)
        //TODO: 異常系
    }
}
