package com.xwj.provider;

import com.xwj.consumer.service.EchoServiceApi;
import com.xwj.provider.exporter.RpcProxyServer;
import com.xwj.provider.service.EchoServiceApiImpl;

/**
 * 启动时发布服务
 */
public class ProviderTest {

	public static void main(String[] args) {
		EchoServiceApi service = new EchoServiceApiImpl();
		RpcProxyServer proxyServer = new RpcProxyServer();
		proxyServer.publisher(service, 8080);
	}

}