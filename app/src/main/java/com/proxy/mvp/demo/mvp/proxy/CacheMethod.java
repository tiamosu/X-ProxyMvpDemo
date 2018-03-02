package com.proxy.mvp.demo.mvp.proxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xia
 * @date 2018/3/2.
 * <p>
 * 利用此注解，可使指定方法在proxy层是否被缓存
 */

@SuppressWarnings("WeakerAccess")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheMethod {
    boolean isCached() default true;
}
