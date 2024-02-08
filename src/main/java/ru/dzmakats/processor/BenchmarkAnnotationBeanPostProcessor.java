package ru.dzmakats.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

/**
 * Created by Denis Zolotarev on 08.02.2024
 */

@Component
public class BenchmarkAnnotationBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class<?>> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        if (aClass.isAnnotationPresent(Benchmark.class) || methodIsAnnotationPresent(aClass)) {
            map.putIfAbsent(beanName, aClass);
            System.out.printf(">>>> Before init %s%n", beanName);
        }
        return bean;
    }

    private boolean methodIsAnnotationPresent(Class<?> aClass) {
        return Arrays.stream(aClass.getMethods())
                .anyMatch(method -> method.isAnnotationPresent(Benchmark.class));
    }

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        if (!isNull(map.get(beanName))) {
            bean = proxy(bean);
            System.out.printf("<<<< After init %s%n", beanName);
        }
        return bean;
    }

    private Object proxy(Object bean) {
        MethodInterceptor handler = (obj, method, args, proxy) -> {
            Object result;
            if (bean.getClass().isAnnotationPresent(Benchmark.class)
                    || method.isAnnotationPresent(Benchmark.class)) {
                System.out.printf("===== Method %s started =====%n", method.getName());
                long time = System.nanoTime();
                result = proxy.invoke(bean, args);
                time = System.nanoTime() - time;
                System.out.printf("===== Method %s complete. Time = %.3f ms =====%n%n", method.getName(), time / 1e6);
            } else {
                result = proxy.invoke(bean, args);
            }

            return result;
        };

        Object proxy = Enhancer.create(bean.getClass(), handler);
        System.out.println("Here is proxy for: " + bean);
        return proxy;
    }
}
