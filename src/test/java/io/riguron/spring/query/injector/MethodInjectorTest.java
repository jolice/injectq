package io.riguron.spring.query.injector;


import io.riguron.spring.query.exception.InvalidInjectionPointException;
import io.riguron.spring.query.resolver.QueryResolver;
import org.junit.Test;

import static io.riguron.mocks.Mocks.mock;

public class MethodInjectorTest {

    @Test(expected = InvalidInjectionPointException.class)
    public void inject() {
        QueryResolver queryResolver = mock(QueryResolver.class);
        MethodInjector methodInjector = new MethodInjector(queryResolver);
        methodInjector.inject(new InvalidMethodRepository());
    }
}