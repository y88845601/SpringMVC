package com.xiao.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 时间处理类
 * <p>Title: MyDate.java</p>
 * @author xxy 
 * @date 2014-5-23 下午2:09:29 
 * @version V1.0
 */
public class MyDate {
	
	/**
	 * 
	 * 传入需要的时间格式,返回格式化输出当前时间
	 * 修改者名字   xxy
	 * 修改日期   2014-5-23
	 * 修改内容
	 * @param  strFormat	时间格式
	 * @return String
	 */
	public static String MDate(String strFormat){
		DateFormat format = new SimpleDateFormat(strFormat);
		Date date = new Date();
		return format.format(date);
	}
	
	/**
	 * 传入需要的时间格式、时间,返回格式化后的时间
	 * 修改者名字   xxy
	 * 修改日期   2014-8-27
	 * 修改内容
	 * @param @param strFormat
	 * @param @param date
	 * @param @return
	 * @return String
	 */
	public static String MDate(String strFormat,Date date){
		DateFormat format = new SimpleDateFormat(strFormat);
		return format.format(date);
	}
	
	/**
	 * 将String格式转换成时间格式
	 * 修改者名字   xxy
	 * 修改日期   2014-9-23
	 * 修改内容
	 * @param @param strFormat 时间的格式
	 * @param @param strDate	时间的字符串
	 * @param @return
	 * @return Date
	 */
	public static Date getDate(String strFormat,String strDate){
		DateFormat format = new SimpleDateFormat(strFormat);
		Date d = null;
		try {
			d = format.parse(strDate);
		} catch (ParseException e) {
			System.out.println("转换失败");
			e.printStackTrace();
		}
		return d;
	}
	

}
