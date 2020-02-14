package io.riguron.spring.query.injector;

import io.riguron.spring.query.SqlQuery;
import io.riguron.spring.query.exception.InvalidInjectionPointException;
import io.riguron.spring.query.resolver.QueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

@RequiredArgsConstructor
@Component
public final class MethodInjector implements Injector {

    private final QueryResolver queryResolver;

    @Override
    public void inject(Object bean) {
        for (Method method : bean.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(SqlQuery.class)) {
                if (method.getParameterCount() != 1 || !method.getParameterTypes()[0].equals(String.class)) {
                    throw new InvalidInjectionPointException("Methods annotated with @SqlQuery must define only one argument of the String type");
                }
                ReflectionUtils.makeAccessible(method);
                ReflectionUtils.invokeMethod(method, bean, queryResolver.resolve(method.getDeclaredAnnotation(SqlQuery.class)));
            }
        }
    }

}