package io.riguron.spring.query.injector;

import io.riguron.spring.query.SqlQuery;
import io.riguron.spring.query.exception.InvalidInjectionPointException;
import io.riguron.spring.query.resolver.QueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@RequiredArgsConstructor
@Component
public class FieldInjector implements Injector {

    private final QueryResolver queryResolver;

    @Override
    public void inject(Object bean) {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(SqlQuery.class)) {
                if (!field.getType().equals(String.class)) {
                    throw new InvalidInjectionPointException("Fields annotated with @SqlQuery must be of a String type");
                }
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, bean, queryResolver.resolve(field.getDeclaredAnnotation(SqlQuery.class)));
            }
        }
    }
}