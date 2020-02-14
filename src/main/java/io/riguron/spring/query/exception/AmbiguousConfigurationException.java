package io.riguron.spring.query.exception;

public class AmbiguousConfigurationException extends RuntimeException {

    public AmbiguousConfigurationException() {
        super("Multiple classes annotated with @EnableInjectQuery found, at most one configuration point is allowed");
    }
}
