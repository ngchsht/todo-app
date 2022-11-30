package com.example.todoapp.model

data class Task(
    val id: Int,
    val title: String,
    val completed: Boolean
)

data class CreateTaskBody(
    val title: String
)

data class UpdateTaskBody(
    val completed: Boolean
)
