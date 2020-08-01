package com.xwj.provider.exporter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * RPC服务发布者
 * 
 * 1、监听客户端的TCP连接，接收到新的客户端连接之后，将其封装成Task，由线程池执行
 * 
 * 2、利用反射调用指定的业务类中的方法，将最终结果写入到socket管道中返回给客户端
 * 
 * 3、远程调用完成之后，释放Socket等连接资源，防止句柄泄露
 */
public class RpcServer {

	/** 定义一个线程池 */
	private ExecutorService executorService = Executors.newCachedThreadPool();

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
