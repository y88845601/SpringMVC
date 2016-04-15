package com.xiao.util;

import javax.servlet.http.HttpServletRequest;

/**
 * IP相关的方法类
 * <p>Title: IPRemort.java</p>
 * @author xxy 
 * @date 2014-9-6 下午5:04:27 
 * @version V1.0
 */
public class IPRemort {
	
	/**
	 * 获取请求信息中的IP值
	 * 修改者名字   xxy
	 * 修改日期   2014-9-6
	 * 修改内容
	 * @param @param request
	 * @param @return
	 * @return String
	 */
	public static String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}   
		return request.getHeader("x-forwarded-for");  
	}
	
	

}
