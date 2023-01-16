package com.fastcampus.projectboard.controller;

import com.fastcampus.projectboard.config.SecurityConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@DisplayName("View 컨트롤러 - 게시글")
@Import(SecurityConfig.class) // security 인증을 허용해줌
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {
    private final MockMvc mvc;

    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {
        // Given

        // when & then
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())    // 검사식
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))  // view를 검사하므로 TEXT_HTML 타입인지 검사한다, 호환되는 타입까지도 검사해서 허용해준다(더 넓은 범위의 허용)
                .andExpect(view().name("articles/index"))   // 어떤 뷰 네임이 있어야한다.
                .andExpect(model().attributeExists("articles")); // attribute가 있나 없나를 검사한다.

    }


    @DisplayName("[view][GET] 게시글 상세 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleView_thenReturnsArticleView() throws Exception {
        // Given

        // when & then
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())    // 검사식
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))  // view를 검사하므로 TEXT_HTML 타입인지 검사한다
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("article")) // attribute가 있나 없나를 검사한다.
                .andExpect(model().attributeExists("articleComments")); // 댓글들이 있나 검사

    }

    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 검색 전용 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleSearchView_thenReturnsArticleSearchView() throws Exception {
        // Given

        // when & then
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())    // 검사식
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))  // view를 검사하므로 TEXT_HTML 타입인지 검사한다
                .andExpect(model().attributeExists("articles/search"));
    }

    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 해시태그 검색 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleHashtagSearchView_thenReturnsArticleHashSearchView() throws Exception {
        // Given

        // when & then
        mvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())    // 검사식
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("articles/search-hashtag"));
    }
}