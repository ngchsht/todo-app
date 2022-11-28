package com.example.todoapp.repository

import com.example.todoapp.model.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet


interface TodoRepositoryInterface {
    fun getAllTasks(): MutableList<Task>
    fun createTask(title: String): Task
}

@Repository
class TodoRepositoryImpl: TodoRepositoryInterface {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    override fun getAllTasks(): MutableList<Task> {
        return jdbcTemplate.query(
        "SELECT id, title, completed FROM task"
        ) { rs: ResultSet, _:Int ->
            Task(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getBoolean("completed")
            )
        }
    }

    override fun createTask(title: String): Task {
        return jdbcTemplate.query(
        "INSERT INTO task (title) VALUES ('${title}') RETURNING id, title, completed;"
        ) { rs: ResultSet, _:Int ->
            Task(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getBoolean("completed")
            )
        }[0]
    }
}