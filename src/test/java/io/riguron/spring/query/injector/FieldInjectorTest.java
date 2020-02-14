package io.riguron.spring.query.injector;

import io.riguron.spring.query.exception.InvalidInjectionPointException;
import io.riguron.spring.query.resolver.QueryResolver;
import org.junit.Test;

import static io.riguron.mocks.Mocks.mock;

public class FieldInjectorTest {

    @Test(expected = InvalidInjectionPointException.class)
    public void inject() {
        QueryResolver queryResolver = mock(QueryResolver.class);
        FieldInjector fieldInjector = new FieldInjector(queryResolver);
        fieldInjector.inject(new InvalidFieldRepository());
    }
}