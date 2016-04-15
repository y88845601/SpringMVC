package com.test;


public class Escape {

	public static void main(String[]args){
		String a = "asdasd-hjguj-123213";
		System.out.println(a.substring(a.indexOf("-")+1, a.length()));
		System.out.println(a.substring(0, a.indexOf("-")));
	}
	
}
