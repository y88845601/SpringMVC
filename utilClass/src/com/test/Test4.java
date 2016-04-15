package com.test;

import java.util.HashMap;
import java.util.Map;

import com.xiao.util.DataFromURL;

public class Test4 {
	public void getData(int a){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("mm", 0);
		map.put("point", "(113.711767,34.731127)");
		map.put("pageNow", a);
		try {
			DataFromURL.getDataFromURL("http://192.168.1.23:8080/linlangApp/servlet/GPRSProductServlet", map);
			a = a++;
			/*System.out.println(com.xiao.util.MyDate.MDate("yyyyMMdd hhmmss"));*/
			System.out.println(a);
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}
	
	public void thread(){
		Thread thread1 = new Thread(){
			public void run(){
				for(int j = 0;j<100;j++){
					Thread thread = new Thread(){
						public void run(){
							Test4 test = new Test4();
							for(int i = 0;i<3;i++){
								test.getData(i);
							}
						}
					};
					thread.start();
				}
			}
		};
		thread1.start();
	}
	
	public static void main(String [] args){
		Test4 test = new Test4();
//		for(int m = 0;m<100;m++){
//			test.thread();
//		}
		
		for(int i = 0;i >= 0;i++){
			for(int m = 0;m<3;m++){
				test.getData(m);
			}
		}
	}



}
