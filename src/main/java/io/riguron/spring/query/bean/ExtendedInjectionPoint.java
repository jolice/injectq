package io.riguron.spring.query.bean;

import io.riguron.spring.query.SqlQuery;
import io.riguron.spring.query.resolver.QueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.AutowireCandidateResolver;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExtendedInjectionPoint implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
        defaultListableBeanFactory.setAutowireCandidateResolver(new AutowireCandidateResolver() {
            @Override
            public Object getSuggestedValue(DependencyDescriptor descriptor) {
                SqlQuery query = descriptor.getAnnotation(SqlQuery.class);
                if (query != null) {
                    return beanFactory.getBean(QueryResolver.class).resolve(query);
                }
                return null;
            }
        });
    }
}
