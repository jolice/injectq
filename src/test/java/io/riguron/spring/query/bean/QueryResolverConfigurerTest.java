package io.riguron.spring.query.bean;

import io.riguron.spring.query.EnableInjectQuery;
import io.riguron.spring.query.exception.AmbiguousConfigurationException;
import org.junit.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import static io.riguron.mocks.Mocks.mock;
import static io.riguron.mocks.Mocks.when;
import static io.riguron.mocks.matcher.ArgumentMatchers.eq;

public class QueryResolverConfigurerTest {

    @Test(expected = AmbiguousConfigurationException.class)
    public void postProcessBeanFactory() {
        ConfigurableListableBeanFactory beanFactory = mock(ConfigurableListableBeanFactory.class);
        when(beanFactory.getBeanNamesForAnnotation(eq(EnableInjectQuery.class)))
                .thenReturn(new String[]{"first", "seconds"});
        new QueryResolverConfigurer().postProcessBeanFactory(beanFactory);

    }
}