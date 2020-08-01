package com.xwj.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.xwj.provider.exporter.RpcServer;

@Configuration
@ComponentScan(basePackages = "com.xwj.provider.service")
public class SpringConfig {

	@Bean(name = "rpcServer")
	public RpcServer rpcServer() {
		return new RpcServer();
	}

}