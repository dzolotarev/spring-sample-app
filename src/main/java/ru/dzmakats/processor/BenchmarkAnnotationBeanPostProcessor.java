package ru.dzmakats.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by Denis Zolotarev on 08.02.2024
 */

@Component
public class BenchmarkAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Class<?> aClass = bean.getClass();
        if (aClass.isAnnotationPresent(Benchmark.class) || methodIsAnnotationPresent(aClass)) {
            System.out.printf(">>>> Before init %s%n", beanName);
        }
        return bean;
    }

    private boolean methodIsAnnotationPresent(Class<?> aClass) {
        return Arrays.stream(aClass.getMethods())
                .anyMatch(method -> method.isAnnotationPresent(Benchmark.class));
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.printf("<<<< After init %s%n", beanName);
        return bean;
    }
}
