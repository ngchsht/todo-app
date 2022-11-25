package com.example.todoapp.repository

import com.example.todoapp.model.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet


interface TodoRepositoryInterface {
    fun getAllTasks(): MutableList<Task>
}

@Repository
class TodoRepositoryImpl: TodoRepositoryInterface {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    override fun getAllTasks(): MutableList<Task> {
        return jdbcTemplate.query(
                """select id, title, completed from task"""
        ) { rs: ResultSet, _:Int ->
            Task(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getBoolean("completed")
            )
        }
    }
}