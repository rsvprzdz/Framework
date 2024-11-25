package com.kh.khEmail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.kh.khEmail.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * 사용자 정보 추가 (C)
     * @param user 사용자 정보
     * @return "success" : 성공, "failed" : 실패
     */
    @PostMapping("")
    public String addUser(@RequestBody User user) {
        log.info("---> user : {}", user);

        int result = service.addUser(user);

        return result == 1 ? "success" : "failed";
    }

    /**
     * 사용자 정보 조회 (R)
     * @param id    사용자 아이디
     * @return  조회된 사용자 정보
     * @throws Exception
     */
    @GetMapping("")
    public String selectUser(String id) throws Exception {
        return new Gson().toJson(service.selectUser(id));
    }

    /**
     * 사용자 정보 변경 (U)
     * @param user  변경할 사용자 정보
     * @return 1: 변경 성공, 0: 변경 실패
     */
    @PutMapping("")
    public int updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    /**
     * 사용자 정보 삭제 (D)
     * @param id    삭제할 사용자 아이디
     * @return 1: 삭제 성공, 0: 삭제 실패
     */
    @DeleteMapping("")
    public int deleteUser(@RequestParam String id) {
        return service.deleteUser(id);
    }
}