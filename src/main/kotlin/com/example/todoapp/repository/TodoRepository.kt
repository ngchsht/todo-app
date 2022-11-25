package com.example.todoapp.repository

import com.example.todoapp.model.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet


interface TodoRepositoryInterface {
    fun getAllTasks(): MutableList<Unit>
}

@Repository
class TodoRepositoryImpl: TodoRepositoryInterface {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    override fun getAllTasks(): List<Task> {
//        val sql = (""
//                + "SELECT"
//                + " *"
//                + " FROM"
//                + " task")
//        val rowMapper: RowMapper<Task> = BeanPropertyRowMapper<Task>(Task::class.java)
//        return jdbcTemplate.query(sql, rowMapper)
//        return jdbcTemplate.queryForObject(sql, DataClassRowMapper(Array<Task>::class.java))!!
//        return jdbcTemplate.query(
//                """select id, title, completed from todo"""
//        ) { rs: ResultSet, _:Int ->
//            Todo(
//                rs.getInt("id"),
//                rs.getString("title"),
//                rs.getBoolean("completed")
//            )
//        }
    }
}