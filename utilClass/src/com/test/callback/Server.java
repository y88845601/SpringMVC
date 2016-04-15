package com.test.callback;

/**
 * 模拟CS架构中异步回调<br>
 * 回调模式-模拟服务端类
 * @author xingyuan
 * @date 2016-3-28
 * <!------------------>
 */
public class Server {
	
	public void getClientMsg(CSCallback	csCallBack,String msg){
		System.out.println("服务端：服务端接收到客户端发送的消息为:"+msg);
		//模拟服务端对数据进行处理
		try{
			System.out.println("服务端数据处理中...");
			Thread.sleep(5 * 1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("服务端：数据处理成功,返回状态 200");
		String status = "200";
		csCallBack.process(status);
	}

}
