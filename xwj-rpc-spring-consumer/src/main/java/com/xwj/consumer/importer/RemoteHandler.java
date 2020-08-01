package com.xwj.consumer.importer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.xwj.consumer.request.RpcRequest;

/**
 * 使用JDK动态代理，构建RpcRequest请求对象
 */
public class RemoteHandler implements InvocationHandler {

	private String host;
	private int port;

	public RemoteHandler(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("proxy come in~~~ ");

		// 1、初始化request对象
		RpcRequest rpcRequest = new RpcRequest();
		rpcRequest.setClassName(method.getDeclaringClass().getName());
		rpcRequest.setMethodName(method.getName());
		rpcRequest.setParameters(args);

		// 2、发送请求
		RpcSender rpcSender = new RpcSender(host, port);
		Object result = rpcSender.send(rpcRequest);

		// 3、返回收到的结果
		return result;
	}

}