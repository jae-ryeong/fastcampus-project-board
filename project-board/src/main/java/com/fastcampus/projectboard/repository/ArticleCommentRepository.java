package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.ArticleComment;
import com.fastcampus.projectboard.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment> {
    @Override   // Alt + Insert키를 눌러 QuerydslBinderCustomizer의 메서드 구현
    default void customize(QuerydslBindings bindings, QArticleComment root) {
        bindings.excludeUnlistedProperties(true);   // 원하는 항목만 검색가능
        bindings.including(root.content, root.createdAt, root.createdBy); // 원하는 필드 추가
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // 쿼리문이 like '%${value}%'
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);    // createAt은 문자열이므로 DateTime을 쓰고, eq는 완전 동일한 조건을 검색하는데 여기서는 시,분,초 까지 정확해야함
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}