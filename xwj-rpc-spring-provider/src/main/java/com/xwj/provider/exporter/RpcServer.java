package com.xwj.provider.exporter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.xwj.provider.ann.RpcService;

/**
 * 在spring实例化RpcProxyServer的时候，就可以自动发布服务了
 */
@Component
public class RpcServer implements ApplicationContextAware, InitializingBean {

	ExecutorService executorService = Executors.newCachedThreadPool();

	/** 标记为Component之后，这里最好初始化，否则依赖注入的时候会报错 */
	private int port = 8080;
	private Map<String, Object> handlerMap = new HashMap<String, Object>();

	/**
	 * 拿到实现了RpcService注解的类，并放入到handlerMap中，这里的handlerMap就类似于IOC容器
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// 基于注解的方式，拿到实现了RPCService注解的类
		Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
		System.out.println("=======" + serviceBeanMap);
		if (serviceBeanMap != null && !serviceBeanMap.isEmpty()) {
			for (Object serviceBean : serviceBeanMap.values()) {
				RpcService rpcServiceAnno = serviceBean.getClass().getAnnotation(RpcService.class);
				String serviceName = rpcServiceAnno.value().getName();// 可以拿到对应的注解的值

				// 发布服务的时候带上版本信息
				String versionValue = rpcServiceAnno.version();
				if (versionValue != null && versionValue.length() > 0) {
					serviceName = serviceName + "-" + versionValue;
				}
				System.out.println(serviceName + ":" + serviceBean);

				handlerMap.put(serviceName, serviceBean);
			}
		}
		System.out.println(handlerMap.toString());
	}

	/**
	 * 初始化bean时
	 */
	public void afterPropertiesSet() throws Exception {
		publisher(handlerMap, port);
	}

	/**
	 * 接收客户端的请求并将结果返回
	 * 
	 * @param target
	 *            对外提供的服务
	 * @param port
	 *            对外提供服务的端口
	 */
	public void publisher(Object target, int port) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);

			while (true) {// 通过循环不断接受请求
				Socket socket = serverSocket.accept();// 监听客户端的请求

				// 每一个socket交给一个processorhandler处理，这里的target就是真正的业务类
				executorService.execute(new ProcessorHandler(socket, target));// 处理客户端的请求
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}