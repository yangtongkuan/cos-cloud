package com.cos.cloud;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.persistence.EntityManager;

@SpringBootApplication
@EnableJms
@EnableAsync
public class CosCloudUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CosCloudUserApplication.class, args);
    }

    @Bean
    public JPAQueryFactory jpaQuery(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
