package io.riguron.spring.query;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(InjectQueryConfiguration.class)
public @interface EnableInjectQuery {

    String path() default "";

    String extension() default "sql";
}
