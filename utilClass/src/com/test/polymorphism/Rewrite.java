package com.test.polymorphism;

/**
 * 定义Person的一个派生类Reload,重写基类中的out方法
 * @author xingyuan
 * @date 2016-3-26
 * <!------------------>
 */
public class Rewrite extends Person {

	public Rewrite(String str){
		System.out.println(str);
	}
	
	public Rewrite(){}
	
	public void out(){
		System.out.println("this is rewrite");
	}
	
}
