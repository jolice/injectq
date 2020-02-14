package io.riguron.spring.query.config;

import io.riguron.spring.query.EnableInjectQuery;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableInjectQuery(path = "scripts")
@Profile("config")
public class InjectSqlConfiguration {
}
