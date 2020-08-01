package com.xwj.consumer;

import com.xwj.consumer.importer.RpcProxyClient;
import com.xwj.consumer.service.EchoServiceApi;

/**
 * 测试RPC服务消费者
 */
public class ConsumerTest {

	public static void main(String[] args) {
		// 很单纯的一个类，用于生成代理
		RpcProxyClient rpcProxyClient = new RpcProxyClient();

		// 需要构建一个代理
		EchoServiceApi service = rpcProxyClient.clientProxy(EchoServiceApi.class, "localhost", 8080);
		String result = service.echo("are you ok");
		System.out.println(result);
	}

}