package com.xwj.provider.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 标示需要发布的服务
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component // 意味着加了这个RPCService注解的类可以被spring托管
public @interface RpcService {

	Class<?> value();

	String version() default "";

}