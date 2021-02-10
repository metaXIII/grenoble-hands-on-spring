package com.zenika.handson.spring.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(controllers = [CitiesController::class])
internal class CitiesControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `get all cities return 200`() {
        mockMvc.get("/cities")
            .andExpect { status { isOk() } }
            .andExpect {
                content {
                    json(javaClass.getResource("/contract/cities/GET_CITIES.json").readText())
                }
            }
    }

    @Test
    fun `get existing city return 200`() {
        mockMvc.get("/cities/GRENOBLE")
            .andExpect { status { isOk() } }
            .andExpect {
                content {
                    json(javaClass.getResource("/contract/cities/GET_CITY.json").readText())
                }
            }
    }

    @Test
    fun `get unknown city return 404`() {
        mockMvc.get("/cities/NOWHERE")
            .andExpect { status { isNotFound() } }
    }

}