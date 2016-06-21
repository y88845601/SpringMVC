package com.xxy.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.xxy.filter.WordFilter;

/**
 * 接收传递进来的参数,并转化为想要的类型
 * <p>Title: Parameters.java</p>
 * @author xxy 
 * @date 2014-8-7 下午4:21:52 
 * @version V1.0
 */
public class Parameters {
	
	/**
	 * 获取Long类型的参数
	 * 修改者名字   xxy
	 * 修改日期   2014-8-8
	 * 修改内容
	 * @param @param request
	 * @param @param name
	 * @param @return
	 * @return Long
	 */
	public static Long getLongParameter(HttpServletRequest request,String name){
		String str = request.getParameter(name);
		Long a = null;
		if(str != null && str != "" && !"null".equals(str)){
			a = Long.parseLong(str);
		}
		return a;
	}
	
	/**
	 * 获取int类型的参数,返回-1000000000表示接收数据失败或参数为空
	 * 修改者名字   xxy
	 * 修改日期   2014-8-8
	 * 修改内容
	 * @param @param request
	 * @param @param name
	 * @param @return
	 * @return int
	 */
	public static int getIntParameter(HttpServletRequest request,String name){
		String str = request.getParameter(name);
		int a = -1000000000;
		if(str != null && str != "" && !"null".equals(str)){
			a = Integer.parseInt(str);
		}
		return a;
	}
	
	/**
	 * 获取Double类型的参数
	 * 修改者名字   xxy
	 * 修改日期   2014-8-8
	 * 修改内容
	 * @param @param request
	 * @param @param name
	 * @param @return
	 * @return Double
	 */
	public static Double getDoubleParameter(HttpServletRequest request,String name){
		String str = request.getParameter(name);
		Double a = null;
		if(str != null && str != "" && !"null".equals(str)){
			a = Double.parseDouble(str);
		}
		return a;
	}
	
	/**
	 * 获取String类型的参数
	 * 修改者名字   xxy
	 * 修改日期   2014-8-8
	 * 修改内容
	 * @param @param request
	 * @param @param name
	 * @param @return
	 * @return String
	 */
	public static String getStringParameter(HttpServletRequest request,String name){
		String str = request.getParameter(name);
		WordFilter wf = new WordFilter();
		return wf.init(str);
	}
	
	/**
	 * 获取一个Date类型的参数
	 * 修改者名字   xxy
	 * 修改日期   2014-8-8
	 * 修改内容
	 * @param @param request
	 * @param @param name
	 * @param @param dateFormat	输出时间的格式
	 * @param @return
	 * @param @throws ParseException
	 * @return Date
	 */
	public static Date getDateParameter(HttpServletRequest request,String name,String dateFormat) throws ParseException{
		String str = request.getParameter(name);
		Date a = null;
		if(str != null && str != "" && !"null".equals(str)){
			DateFormat df = new SimpleDateFormat(dateFormat);
			a = (Date) df.parseObject(str);
		}
		return a;
	}

}
