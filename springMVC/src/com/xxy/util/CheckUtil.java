package com.xxy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 各种判断方法
 * @author xingyuan
 * @date 2016-3-9
 * <!------------------>
 */
public class CheckUtil {

	/**
	 * 检查手机号
	 * @author xingyuan
	 * @date 2016-3-9
	 * <!------------------>
	 * @param mobile
	 * @return boolean
	 */
	public static boolean mobileFormat(String mobile) {
//		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,3-9]))\\d{8}$");
		Pattern p = Pattern.compile("^(1[^1^6^9][0-9]\\d{8}$)");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}
	
	public static void main(String[]args){
		System.out.println(mobileFormat("19939502003"));
	}
	
}
