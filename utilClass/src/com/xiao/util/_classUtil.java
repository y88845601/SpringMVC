package com.xiao.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 公共方法类
 * <p>Title: _classUtil.java</p>
 * @author xxy 
 * @date 2014-9-5 下午4:26:03 
 * @version V1.0
 */
public class _classUtil {
	
	/**
	 * log4g打印 抛出的异常信息
	 * 修改者名字   xxy
	 * 修改日期   2014-9-5
	 * 修改内容
	 * @param @param t
	 * @param @return
	 * @return String
	 */
	public static String getTrace(Throwable t) {
		StringWriter stringWriter= new StringWriter();
		PrintWriter writer= new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer= stringWriter.getBuffer();
		return buffer.toString();
	}

}
