package com.xwj.consumer.request;

import java.io.Serializable;

/**
 * 表示请求的对象，一定要记得序列化
 */
public class RpcRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 服务对象名 */
	private String className;

	/** 方法名 */
	private String methodName;

	/** 参数名 */
	private Object[] parameters;

	/** 版本号 */
	private String version;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}