package com.xwj.consumer.importer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.xwj.consumer.request.RpcRequest;

/**
 * 发送请求并读取服务端返回结果
 */
public class RpcSender {

	private String host;
	private int port;

	public RpcSender(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public Object send(RpcRequest request) {
		Socket socket = null;
		Object result = null;
		ObjectOutputStream outputStream = null;
		ObjectInputStream inputStream = null;

		try {
			// 1、与服务端建立连接
			socket = new Socket(host, port);
			outputStream = new ObjectOutputStream(socket.getOutputStream());

			// 2、向通信管道中写入请求对象
			outputStream.writeObject(request);
			outputStream.flush();

			// 3、读取服务端返回的结果
			inputStream = new ObjectInputStream(socket.getInputStream());
			result = inputStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4、关闭流的操作，用到的方式比较原始
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}