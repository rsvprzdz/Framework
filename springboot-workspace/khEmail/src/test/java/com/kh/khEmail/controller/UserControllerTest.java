package com.kh.khEmail.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.kh.khEmail.model.vo.User;
import com.kh.khEmail.service.UserService;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UserService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("사용자 정보 추가 테스트")
    void addUser() throws Exception {

        User u = new User(1, "홍길동", "hong00", "glasowk12");

        mockMvc.perform(
                post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(u))
        )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("사용자 정보 조회 테스트")
    void selectUser() throws Exception {
        mockMvc.perform(
                get("/user")
                        .content("hong00")
        )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("사용자 정보 수정 테스트")
    void updateUser() throws Exception {
        User u = new User(1, "홍길동", "hong00", "rkqhwk!!");
        mockMvc.perform(
                put("/user")
                    .content(new Gson().toJson(u))
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("사용자 정보 삭제 테스트")
    void deleteUser() throws Exception {
        mockMvc.perform(
                delete("/user")
        ).andExpect(status().isOk());
    }
}