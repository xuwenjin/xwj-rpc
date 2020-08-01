package com.xwj.provider.service;

import com.xwj.consumer.service.UserServiceApi;
import com.xwj.provider.ann.RpcService;

/**
 * 对外接口实现类
 */
@RpcService(value = UserServiceApi.class, version = "1.0")
public class UserServiceImpl implements UserServiceApi {

	public String getUserInfo(String name) {
		return "I am zhangsan";
	}

}