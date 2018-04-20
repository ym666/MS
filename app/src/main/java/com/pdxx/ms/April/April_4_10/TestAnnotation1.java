package com.pdxx.ms.April.April_4_10;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @interface 注解
 * target 元注解 表明此注解用在哪个地方
 * retention 元注解 表明此注解保留到什么时候
 * <p>
 * 定义注解 -----------实现注解管理器--------------使用注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TestAnnotation1 {
    String test() default "998";

    boolean testBoolean() default true;

    int testInt() default 1;


}
