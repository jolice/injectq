package io.riguron.spring.query.bean;

import io.riguron.spring.query.EnableInjectQuery;
import io.riguron.spring.query.exception.AmbiguousConfigurationException;
import io.riguron.spring.query.resolver.QueryResolver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class QueryResolverConfigurer implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] components = beanFactory.getBeanNamesForAnnotation(EnableInjectQuery.class);
        if (components.length > 0) {
            if (components.length != 1) {
                throw new AmbiguousConfigurationException();
            }
            beanFactory.getBean(QueryResolver.class).configure(
                    Objects.requireNonNull(beanFactory.findAnnotationOnBean(components[0], EnableInjectQuery.class))
            );
        }
    }
}
