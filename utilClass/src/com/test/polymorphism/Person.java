package com.test.polymorphism;

/**
 * 基类的实现
 * @author xingyuan
 * @date 2016-3-26
 * <!------------------>
 */
public class Person implements Writeout {
	
	private String name = new String();
	
	public void setName(String str){
		this.name = str;
	}
	
	public void out(){
		System.out.println(name);
	}
	//实现接口的方法
	public void output(){
		System.out.println("interface out!");
	}
	
	public static void main(String [] args){
		Person p1,p2;
		Reload load = new Reload();
		Rewrite write = new Rewrite();
		p1 = load;
		p2 = write;
		Reload newReload = (Reload) p1;
		p1.setName("new Sun");
		p1.out();
		newReload.out("sun");
		p2.out();
		p1.output();
		p2.output();
		//Rewrite newRewrite = (Rewrite) p2;
	}

}
