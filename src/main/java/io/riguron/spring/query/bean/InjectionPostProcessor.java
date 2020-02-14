package io.riguron.spring.query.bean;

import io.riguron.spring.query.injector.Injector;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InjectionPostProcessor implements BeanPostProcessor {

    private final List<Injector> injectors;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        injectors.forEach(injector -> injector.inject(bean));
        return bean;
    }
}
