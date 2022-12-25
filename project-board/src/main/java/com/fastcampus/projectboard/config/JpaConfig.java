package com.fastcampus.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware(){     // jpaauditing을 할때마다 사람이름(작성자)는 이 메서드로 넣게 된다.
        return () -> Optional.of("wofud");  // TODO: 스프링 시큐리티로 인증 기능을 붙이게 될 때 수정
    }


}
