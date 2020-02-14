package io.riguron.spring.query.resolver;

import io.riguron.spring.query.SqlQuery;
import io.riguron.spring.query.exception.QueryReadException;
import org.junit.Test;

import static io.riguron.mocks.Mocks.mock;
import static io.riguron.mocks.Mocks.when;
import static org.junit.Assert.*;

public class QueryResolverTest {

    @Test(expected = QueryReadException.class)
    public void whenResourceDoesNotExist() {
        SqlQuery sqlQuery = mock(SqlQuery.class);
        when(sqlQuery.value()).thenReturn("undefined");
        when(sqlQuery.charset()).thenReturn("UTF-8");
        new QueryResolver().resolve(sqlQuery);
    }

}