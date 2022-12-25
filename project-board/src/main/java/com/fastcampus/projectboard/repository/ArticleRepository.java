package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository안에 @Repository가 붙어있기때문에 인터페이스에 따로 @Repository를 붙이지 않아도 된다
public interface ArticleRepository extends JpaRepository<Article, Long> {
}