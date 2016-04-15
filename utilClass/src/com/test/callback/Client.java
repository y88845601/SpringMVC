package com.test.callback;

/**
 * 模拟CS架构中异步回调<br>
 * 回调模式-模拟客户端类
 * @author xingyuan
 * @date 2016-3-28
 * <!------------------>
 */
public class Client implements CSCallback {

	private Server server;
	
	public Client(Server server){
		this.server = server;
	}
	
	public void sendMsg(final String msg){
		System.out.println("客户端：发送的消息:"+msg);
		//异步处理放在线程中
		new Thread(new Runnable() {
			public void run() {
				server.getClientMsg(Client.this, msg);
			}
		}).start();
		System.out.println("客户端：异步发送成功");
	}
	
	public void process(String status) {
		System.out.println("客户端：服务端回调状态:"+status);
	}

}
