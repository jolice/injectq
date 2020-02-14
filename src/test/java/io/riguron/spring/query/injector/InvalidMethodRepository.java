package io.riguron.spring.query.injector;

import io.riguron.spring.query.SqlQuery;

public class InvalidMethodRepository {

    private String query;

    @SqlQuery("select")
    public void setQuery(Integer query) {
        this.query = String.valueOf(query);
    }
}
