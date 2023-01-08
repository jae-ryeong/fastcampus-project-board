package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// JpaRepository안에 @Repository가 붙어있기때문에 인터페이스에 따로 @Repository를 붙이지 않아도 된다
@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>, // HAL Explorer에서 모든 필드에대한 검색기능을 추가
        QuerydslBinderCustomizer<QArticle>{
    @Override   // Alt + Insert키를 눌러 QuerydslBinderCustomizer의 메서드 구현
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true);   // 원하는 항목만 검색가능
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy); // 원하는 필드 추가
        // bindings.bind(root.title).first(StringExpression::likeIgnoreCase);  // 쿼리문이 like '${value}'
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // 쿼리문이 like '%${value}%', 중간에 일치하는 단어 검색
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);    // createAt은 문자열이므로 DateTime을 쓰고, eq는 완전 동일한 조건을 검색하는데 여기서는 시,분,초 까지 정확해야함
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);

        // HAL Explorer로 실험이 가능하다.
    }
}
