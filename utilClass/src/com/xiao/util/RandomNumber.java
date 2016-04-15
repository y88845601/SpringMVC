package com.xiao.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 生成各种需要的随机数据
 * <p>Title: RandomNumber.java</p>
 * @author xxy 
 * @date 2014-5-23 上午9:53:01 
 * @version V1.0
 */
public class RandomNumber {
	
	
	/**
	 * 根据时间获取一个随机数，如的日期格式为yyyyMMddHHmmss+自定义个数的随机数
	 * 修改者名字   xxy
	 * 修改日期   2014-5-23
	 * 修改内容
	 * @param @param digit	随机数的位数
	 * @param @param strData	时间格式，如：yyyyMMdd
	 * @param @return
	 * @return String
	 */
	public static String DateRandomNumber(String dateFormat,int digit){
		DateFormat format = new SimpleDateFormat(dateFormat);
		Date date = new Date();
		String drdm = format.format(date);
		if(digit > 0){
			drdm += RandomNum(digit);
		}
		return drdm;
	}

	/**
	 * 获取随机数,最长可获取19位Long型数据
	 * 修改者名字   xxy
	 * 修改日期   2014-5-23
	 * 修改内容
	 * @param @param digit	获取随机数的位数
	 * @param @return
	 * @return int
	 */
	public static Long RandomNum2(int digit){
		int aa = 0;
		int num = 1;
		while(aa <digit){
			num = num * 10;
			aa++;
		}
		Long intRd = Long.parseLong((int)(Math.random()*num)+"");
		String intStr = intRd+"";
		if(intStr.length() < digit){
			if((intRd+"").length() < digit){
				int bb = digit - (intRd+"").length();
				String lucky = "1096900619420353107";	//	幸运数字
				String cc = intRd +lucky.substring(0, bb);
				intRd = Long.parseLong(cc);
			}
		}
		intRd = Math.abs(intRd);
		return intRd;
	}
	
	/**
	 * 生成随机数,适用于9位及其以下的int类型的随机数
	 * 修改者名字   xxy
	 * 修改日期   2014-9-6
	 * 修改内容
	 * @param @param digit
	 * @param @return
	 * @return int
	 */
	public static int RandomNum(int digit){
		int aa = 0;
		int num = 1;
		while(aa < digit){
			num = num * 10;
			aa++;
		}
		int intRd = (int) (Math.random()*num);
		/* 若所得数据位数小于实际所需,则根据幸运数字补足长度	update by xxy in 20140808 begin */
		if((intRd+"").length() < digit){
			int bb = digit - (intRd+"").length();
			String lucky = "10260305067";	//	幸运数字
			String cc = intRd +lucky.substring(0, bb);
			intRd = Integer.parseInt(cc);
		}
		/* 若所得数据位数小于实际所需,则根据幸运数字补足长度	update by xxy in 20140808 end */
		return intRd;
	}
	
	/**
	 * 生成15位数值,生成订单号可用
	 * 修改者名字   xxy
	 * 修改日期   2014-9-6
	 * 修改内容
	 * @param @return
	 * @return int
	 */
	public static Long OrderNumber(){
		
		int a1 = RandomNum(9);
		int a2 = RandomNum(9);
		Long a3 = System.currentTimeMillis();
		
		Long aa = 0L;
		aa = a1+a2+a3;
		int bb = 15-(aa+"").length();
		if(bb > 0){
			for(int a = 0;a < bb;a++){
				int b = RandomNum(1);
				if(b==0){
					b = a+1;
				}
				aa = Long.parseLong(b+""+aa);
			}
		}
		return aa;
	}
	/**
	 * 获取带数字、字母(小写)的字符串
	 * 修改者名字   xxy
	 * 修改日期   2015-6-10
	 * 修改内容
	 * @param @param digit
	 * @param @return
	 * @return String
	 */
	public static String RandomStr(int digit){
		String str = "";
		String str2 = DateRandomNumber("yyMMddHHmmssSSS", 0);
		if(digit < 20){
			str2 = "";
		}else{
			digit = digit - str2.length();
		}
		String [] str1 = {"1","2","3","4","5","6","7","8","9","0","q","w","e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m"};
		for(int i = 0;i<digit;i++){
			int a = RandomNum(2)/2;
			if(a>35){a=a-35;}
			str += str1[a];
		}
		
		return str+str2;
	}
	
/*	*//**
	 * main方法
	 * 修改者名字   xxy
	 * 修改日期   2014-5-23
	 * 修改内容
	 * @param @param args
	 * @return void
	 *//*
	public static void main(String [] args){
		String redirect_uri = "http://manage.lang360.com";
		redirect_uri = java.net.URLEncoder.encode(redirect_uri);
		System.out.println(redirect_uri);
	}*/
}
