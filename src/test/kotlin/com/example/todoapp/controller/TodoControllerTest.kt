package com.example.todoapp.controller

import com.example.todoapp.model.Task
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.junit5.api.DBRider
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate
import kotlin.math.log


@SpringBootTest
@DBRider
class TodoControllerTest {
    val restTemplate: RestTemplate = RestTemplateBuilder().build()

    @Test
    @DataSet(
            value=["task.yml"]
    )
    fun `Todoリストが返却されること`() {
        val expectedTask = Task(1, "first task", false)

        val response = restTemplate.exchange("http://localhost:8080/tasks", HttpMethod.GET, null, Array<Task>::class.java)
        val responseBody: Array<Task> = response.body!!

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(responseBody.size).isEqualTo(3)
        Assertions.assertThat(responseBody[0].id).isEqualTo(expectedTask.id)
        Assertions.assertThat(responseBody[0].title).isEqualTo(expectedTask.title)
        Assertions.assertThat(responseBody[0].completed).isEqualTo(expectedTask.completed)
    }
}
