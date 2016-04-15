package com.test.polymorphism;

/**
 * 定义Person的一个派生类Reload,重载基类中的out方法
 * @author xingyuan
 * @date 2016-3-26
 * <!------------------>
 */
public class Reload extends Person {
	
	public Reload(String str){
		System.out.println(str);
	}
	
	public Reload(){}
	
	//重载基类中的out方法
	public void out(String str){
		System.out.println("this is load "+str);
	}

}
