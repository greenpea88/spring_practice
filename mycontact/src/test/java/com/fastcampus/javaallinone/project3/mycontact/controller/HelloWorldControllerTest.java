package com.fastcampus.javaallinone.project3.mycontact.controller;

import com.fastcampus.javaallinone.project3.mycontact.exception.handler.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class HelloWorldControllerTest {

//    @Autowired
//    private HelloWorldController helloWorldController;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
//                .standaloneSetup(helloWorldController)
                .alwaysDo(print())
                .build();
    }


//    @Test
//    public void helloWorld()
//    {
////        System.out.println("test");
//        System.out.println(helloWorldController.helloWorld());
//
//        assertThat(helloWorldController.helloWorld()).isEqualTo("Hello World");
//    }

    @Test
    public void helloWorld() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/helloWorld")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello World"));

    }

    @Test
    public void helloException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/helloException"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").value("unknown error is occurred"));
    }


}