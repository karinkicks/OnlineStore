package ru.karinkicks.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SimplifiedProductControllerAspect {

    @Before("execution(public * ru.karinkicks.controller.ProductController.*(..))")
    public void allMethodsCallsFindAllProducts() {
        System.out.println("В классе ProductController вызывают метод (FindAllProducts)");
    }
}
