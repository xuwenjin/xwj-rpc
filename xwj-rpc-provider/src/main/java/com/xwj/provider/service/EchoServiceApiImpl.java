package com.xwj.provider.service;

import com.xwj.consumer.service.EchoServiceApi;

/**
 * 对外接口实现类
 */
public class EchoServiceApiImpl implements EchoServiceApi {

	@Override
	public String echo(String ping) {
		return ping != null ? ping + "--> I am ok." : "I am bad.";
	}

}