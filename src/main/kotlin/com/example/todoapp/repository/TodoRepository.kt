package com.example.todoapp.repository

import com.example.todoapp.model.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet


interface TodoRepositoryInterface {
    fun getAllTasks(): MutableList<Task>
    fun createTask(title: String): Task
    fun updateTask(id: Int, completed: Boolean): Boolean
    fun deleteTask(id: Int): Boolean
}

@Repository
class TodoRepositoryImpl: TodoRepositoryInterface {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    override fun getAllTasks(): MutableList<Task> {
        return jdbcTemplate.query(
        "SELECT id, title, completed FROM task ORDER BY id"
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

    override fun updateTask(id: Int, completed: Boolean): Boolean {
        val updatedRowCount = jdbcTemplate.update(
        "UPDATE task SET completed = $completed WHERE id = $id;"
        )
        return updatedRowCount == 1
    }

    override fun deleteTask(id: Int): Boolean {
        val deletedRowCount = jdbcTemplate.update(
        "DELETE FROM task WHERE id = $id;"
        )
        return deletedRowCount == 1
    }
}