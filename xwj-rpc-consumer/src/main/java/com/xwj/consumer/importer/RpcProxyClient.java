package com.xwj.consumer.importer;

import java.lang.reflect.Proxy;

/**
 * 本地服务代理
 * 
 * 1、生成目标类的代理，在真正请求目标的时候其实请求的是InvocationHandler中的invoke的方法
 * 
 * 2、创建Socket客户端，根据指定地址连接远程服务提供者
 * 
 * 3、将远程服务调用所需要的接口类，方法名，参数列表等编码参数发送给服务提供者
 * 
 * 4、 同步阻塞等待服务端返回应答，获取应答之后返回
 */
public class RpcProxyClient {

	/**
	 * @param interfaceCls
	 *            被代理的接口
	 * @param host
	 *            主机
	 * @param port
	 *            端口
	 */
	@SuppressWarnings("unchecked")
	public <T> T clientProxy(final Class<T> interfaceCls, final String host, final int port) {
		return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class<?>[] { interfaceCls },
				new RemoteHandler(host, port));
	}

}