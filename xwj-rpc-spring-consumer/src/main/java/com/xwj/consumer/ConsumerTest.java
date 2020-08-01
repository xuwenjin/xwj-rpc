package com.xwj.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xwj.consumer.config.SpringConfig;
import com.xwj.consumer.importer.RpcProxyClient;
import com.xwj.consumer.service.EchoServiceApi;
import com.xwj.consumer.service.UserServiceApi;

/**
 * 测试RPC服务消费者
 */
public class ConsumerTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		RpcProxyClient rpcProxyClient = context.getBean(RpcProxyClient.class);

		EchoServiceApi echoService = rpcProxyClient.clientProxy(EchoServiceApi.class, "localhost", 8080);
		String echoResult = echoService.echo("are you ok");
		System.out.println(echoResult);

		UserServiceApi userService = rpcProxyClient.clientProxy(UserServiceApi.class, "localhost", 8080);
		String userResult = userService.getUserInfo("xiaoming");
		System.out.println(userResult);
	}

}