package com.xwj.provider.service;

import com.xwj.consumer.service.EchoServiceApi;
import com.xwj.provider.ann.RpcService;

/**
 * 对外接口实现类
 */
@RpcService(value = EchoServiceApi.class, version = "1.0") // 可以通过这种方式去发布
public class EchoServiceApiImpl implements EchoServiceApi {

	@Override
	public String echo(String ping) {
		return ping != null ? ping + "--> I am ok." : "I am bad.";
	}

}