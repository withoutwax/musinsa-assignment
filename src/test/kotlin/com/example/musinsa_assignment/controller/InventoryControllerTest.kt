package com.example.musinsa_assignment.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
internal class InventoryControllerTest @Autowired constructor (val mockMvc: MockMvc, val objectMapper: ObjectMapper)
    {

    val baseUrl = "/api/inventories"

    @Nested
    @DisplayName("GET 전체보기 /api/inventories")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetInventories {

        @Test
        fun `should return all inventories`() {
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].brand_name") { value("A") }
                }
        }
    }
}