package com.example.todoapp.service

import com.example.todoapp.model.Task
import com.example.todoapp.repository.TodoRepositoryInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface TodoServiceInterface {
    fun getTodoList(): ArrayList<Task>
    fun createTodo(title: String): Task
    fun updateTodo(id: Int, completed: Boolean): Boolean
    fun deleteTodo(id: Int): Boolean
}

@Service
class TodoServiceImpl: TodoServiceInterface{
    @Autowired
    lateinit var todoRepository: TodoRepositoryInterface

    override fun getTodoList(): ArrayList<Task> {
        return todoRepository.getAllTasks().toCollection(ArrayList())
    }

    override fun createTodo(title: String): Task {
        return todoRepository.createTask(title)
    }

    override fun updateTodo(id: Int, completed: Boolean): Boolean {
        return todoRepository.updateTask(id, completed)
    }

    override fun deleteTodo(id: Int): Boolean {
        return todoRepository.deleteTask(id)
    }
}
