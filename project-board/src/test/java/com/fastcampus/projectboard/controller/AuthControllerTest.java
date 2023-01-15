package com.fastcampus.projectboard.controller;

import com.fastcampus.projectboard.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@DisplayName("View 컨트롤러 - 인증")
@Import(SecurityConfig.class) // security 인증을 허용해줌
@WebMvcTest
public class AuthControllerTest {

    private final MockMvc mvc;
    public AuthControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view][GET] 로그인 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenLogin_thenReturnsLoginView() throws Exception {
        // Given

        // when & then
        mvc.perform(get("/login"))
                .andExpect(status().isOk())    // 검사식
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));  // view를 검사하므로 TEXT_HTML 타입인지 검사한다

    }
}
