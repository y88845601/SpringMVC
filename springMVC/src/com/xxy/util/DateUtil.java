package com.xxy.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间处理公共方法
 * @author xingyuan
 * @date 2016-3-8
 * <!------------------>
 */
public class DateUtil {
	
	public static String datetimeToStr(Date date){
		if (date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		}
		return null;
	}
	public static String dateToStr(Date date){
		if (date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		}
		return null;
	}
	public static Date strTodate(String dateStr,String fromat){
		if (dateStr!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(fromat);
			try {
				return sdf.parse(dateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 把日期转换为固定格式的字符串
	 */
	public static String dateToStr(Date date,String fromat){
		if (date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(fromat);
			return sdf.format(date);
		}
		return null;
	}
	
	public static Timestamp strToTimestmp(String dateStr,String fromat){
		if (dateStr!=null){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(fromat);
				Date d = sdf.parse(dateStr);
				Timestamp ts = new Timestamp(d.getTime());
				return ts;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 获取当前日期
	 */
	public static Date getCurrDate() throws Exception{
		return new Date();
	}
	
	/**
	 * 获得加上几日后的日期
	 */
	 public static Date addDate(Date d,long day) throws Exception{
		  long time = d.getTime(); 
		  day = day*24*60*60*1000; 
		  time+=day; 
		  return new Date(time);
	}
	 
	 /**
	  * 计算剩余天数
	  */
	 public static Integer subDate(Date beginDate,Date endDate) throws Exception{
		 long  bTime = beginDate.getTime();
		 long  eTime = endDate.getTime();
		 long sub = eTime-bTime;
		 Integer days=(int)(sub/(1000*60*60*24));
		 return days;
	 }
	 
	 public static String formatSecond(Integer second){
		 StringBuffer str = new StringBuffer();
		 if(second==0){
			 str.append(second+"秒");
		 }else{
			 int h=second/3600;
			 int m=(second%3600)/60;
			 int s=(second%3600)%60;
			 if(h>0){
				 str.append(h+"小时");
			 }
			 if(m>0){
				 str.append(m+"份");
			 }
			 if(s>0){
				 str.append(s+"秒");
			 }
		 }
		
		 return str.toString();
	}
	 /**
	  * 计算两个日期的相差小时数
	 * @throws ParseException 
	  */
	 public static Integer hours(String smdate){
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
		 Integer t1 = Integer.parseInt(sdf.format(new Date()));
		 Integer t2 = Integer.parseInt(smdate);
		 return Math.abs(t1-t2);
		 
		 
	 }
	 
	 /**
	  * @param args
	 * @throws Exception 
	  */
	 public static void main(String[] args) throws Exception {
		
	 }
}
