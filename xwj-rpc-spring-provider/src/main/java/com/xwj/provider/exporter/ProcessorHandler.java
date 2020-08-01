package com.xwj.provider.exporter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

import com.xwj.consumer.request.RpcRequest;

/**
 * 处理客户端端请求
 */
public class ProcessorHandler implements Runnable {

	private Socket socket;
	private Object target;

	public ProcessorHandler(Socket socket, Object target) {
		this.socket = socket;
		this.target = target;
	}

	@Override
	public void run() {
		// 用于定义输入流和输出流
		ObjectInputStream objectInputStream = null;
		ObjectOutputStream objectOutputStream = null;

		try {
			objectInputStream = new ObjectInputStream(socket.getInputStream());

			// 1、从socket中读取请求流对象
			RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();

			// 2、调用真正的处理方法
			Object result = invoke(rpcRequest);

			// 3、将结果通过socket输出
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(result);
			objectOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (objectOutputStream != null) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 利用反射处理真正的请求
	 */
	private Object invoke(RpcRequest rpcRequest)
			throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Object[] args = rpcRequest.getParameters();
		Class<?>[] types = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			types[i] = args[i].getClass();
		}

		Class<?> clazz = Class.forName(rpcRequest.getClassName());
		Method method = clazz.getMethod(rpcRequest.getMethodName(), types);

		// 这个target是通过构造方法传递进来的
		return method.invoke(target, args);
	}

}