package io.riguron.spring.query.injector;

import io.riguron.spring.query.SqlQuery;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("invalid-field")
public class InvalidFieldRepository {

    @SqlQuery("field")
    private Integer field;


}