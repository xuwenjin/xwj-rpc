package com.xwj.provider;

import com.xwj.consumer.service.EchoServiceApi;
import com.xwj.provider.exporter.RpcServer;
import com.xwj.provider.service.EchoServiceApiImpl;

/**
 * 启动时发布服务
 */
public class ProviderTest {

	public static void main(String[] args) {
		EchoServiceApi service = new EchoServiceApiImpl();
		RpcServer proxyServer = new RpcServer();
		proxyServer.publisher(service, 8080);
	}

}