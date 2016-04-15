package com.test.callback;

/**
 * 模拟CS架构中异步回调<br>
 * 回调模式-测试类
 * @author xingyuan
 * @date 2016-3-28
 * <!------------------>
 */
public class CallBackMain {
	
	public static void main(String [] args){
		Server server = new Server();
		Client client = new Client(server);
		
		client.sendMsg("Server,Hello~");
		System.out.println("test output sequence");
	}

}
