package com.xwj.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xwj.consumer.importer.RpcProxyClient;

/**
 */
@Configuration
public class SpringConfig {

	@Bean(name = "rpcProxyClient")
	public RpcProxyClient getRpcProxyClient() {
		return new RpcProxyClient();
	}

}