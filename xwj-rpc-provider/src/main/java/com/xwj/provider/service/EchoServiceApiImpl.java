package com.xwj.provider.service;

import com.xwj.consumer.service.EchoServiceApi;

public class EchoServiceApiImpl implements EchoServiceApi {

	@Override
	public String echo(String ping) {
		return ping != null ? ping + "--> I am ok." : "I am bad.";
	}

}