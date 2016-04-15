package com.xxy.util;

/**
 * 小方法集
 * @author xingyuan
 * @date 2016-3-8
 * <!------------------>
 */
public class SmallMethods {
	
	/**
	 * 将数组内的值变成大写
	 * @author xingyuan
	 * @date 2016-4-13
	 * <!------------------>
	 * @param strArr
	 * @return String[]
	 */
	public static String[] strArrayUpperCase(String [] strArr){
		int i = 0;
		for(String s : strArr){
			strArr[i] = s.toUpperCase();
			i++;
		}
		return strArr;
	}
	
	/**
	 * 将数组内的值变成小写
	 * @author xingyuan
	 * @date 2016-4-13
	 * <!------------------>
	 * @param strArr
	 * @return String[]
	 */
	public static String[] strArrayLowerCase(String [] strArr){
		int i = 0;
		for(String s : strArr){
			strArr[i] = s.toLowerCase();
			i++;
		}
		return strArr;
	}

	/**
	 * 将传入数据的首字母做大写处理
	 * @author xingyuan
	 * @date 2016-3-8
	 * <!------------------>
	 * @param str
	 * @return String
	 */
	public static String replaceFirst(String str){
		if(str.length() > 0){
			return str.replaceFirst(str.substring(0,1), str.substring(0, 1).toUpperCase());
		}
		return str;
	}
	
	/**
	 * 去掉字符串最后一个字符
	 * @author xingyuan
	 * @date 2016-3-8
	 * <!------------------>
	 * @param str
	 * @return String
	 */
	public static String ridLastOne(String str){
		return str.substring(0,str.length()-1);
	}
	
	/**
	 * 去掉字符串最后n个字符
	 * @author xingyuan
	 * @date 2016-3-9
	 * <!------------------>
	 * @param str
	 * @param n 去掉的个数
	 * @return String
	 */
	public static String ridLastOne(String str,int n){
		return str.substring(0,str.length()-n);
	}
	
	/**
	 * 去掉字符串第一个字符
	 * @author xingyuan
	 * @date 2016-3-8
	 * <!------------------>
	 * @param str
	 * @return String
	 */
	public static String ridFirstOne(String str){
		return str.substring(1,str.length());
	}
	
	/**
	 * 去掉字符串前n个字符
	 * @author xingyuan
	 * @date 2016-3-8
	 * <!------------------>
	 * @param str
	 * @param n 去掉的个数
	 * @return String
	 */
	public static String ridFirstOne(String str,int n){
		return str.substring(n,str.length());
	}
	
}
