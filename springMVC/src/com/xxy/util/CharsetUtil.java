package com.xxy.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 字符集编码转换
 * <p>Title: CharsetUtil.java</p>
 * @author xxy 
 * @date 2014-8-8 下午2:05:46 
 * @version V1.0
 */
public class CharsetUtil {
	
	public static void  ToUTF8(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	}
	


}
