package com.example.todoapp.controller

import com.example.todoapp.model.CreateTaskBody
import com.example.todoapp.model.Task
import com.example.todoapp.model.UpdateTaskBody
import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.junit5.api.DBRider
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.*
import org.springframework.web.client.RestTemplate

@SpringBootTest
@DBRider
class TodoControllerTest {
    val restTemplate: RestTemplate = RestTemplateBuilder().build()

    @Test
    @DataSet(
        value=["task.yml"]
    )
    fun `Todoリストが返却されること`() {
        val response = restTemplate.exchange("http://localhost:8080/tasks", HttpMethod.GET, null, Array<Task>::class.java)
        val responseBody: Array<Task> = response.body!!

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(responseBody.size).isEqualTo(3)
        Assertions.assertThat(responseBody[0].id).isInstanceOf(Integer::class.java)
        Assertions.assertThat(responseBody[0].title).isEqualTo("1st task")
        Assertions.assertThat(responseBody[0].completed).isEqualTo(false)
    }

    @Test
    @DataSet(
        value=["task.yml"]
    )
    fun `Todoが作成されること`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val createTaskBody: CreateTaskBody = CreateTaskBody("4th task")
        val entity: HttpEntity<CreateTaskBody> = HttpEntity(createTaskBody, headers)

        val response = restTemplate.exchange("http://localhost:8080/tasks", HttpMethod.POST, entity, Task::class.java)
        val responseBody: Task = response.body!!

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
        Assertions.assertThat(responseBody.id).isInstanceOf(Integer::class.java)
        Assertions.assertThat(responseBody.title).isEqualTo("4th task")
        Assertions.assertThat(responseBody.completed).isEqualTo(false)
    }

    @Test
    @DataSet(
            value=["task.yml"]
    )
    fun `Todoが更新されること`() {
        val taskListResponse = restTemplate.exchange("http://localhost:8080/tasks", HttpMethod.GET, null, Array<Task>::class.java)
        val taskListResponseBody: Array<Task> = taskListResponse.body!!
        val id = taskListResponseBody[0].id
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val updateTaskBody: UpdateTaskBody = UpdateTaskBody(true)
        val entity: HttpEntity<UpdateTaskBody> = HttpEntity(updateTaskBody, headers)

        val response = restTemplate.exchange("http://localhost:8080/tasks/${id}", HttpMethod.PUT, entity, Task::class.java)

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.NO_CONTENT)
    }
}


