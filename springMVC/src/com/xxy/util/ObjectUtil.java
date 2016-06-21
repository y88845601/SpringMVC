package com.xxy.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xxy.util.model.DataGridModel;

/**
 * servlet返回信息封装数据公共类
 * <p>Title: ObjectUtil.java</p>
 * @author xxy 
 * @date 2014-7-26 上午10:39:45 
 * @version V1.0
 */
public class ObjectUtil {
	/**
	 * 接口返回无路径信息,带参数集
	 * 修改者名字   xxy
	 * 修改日期   2014-7-26
	 * 修改内容
	 * @param @param request
	 * @param @param response
	 * @param @param objModel	ObjectModel集合类
	 * @param @throws UnsupportedEncodingException
	 * @return void
	 */
	public static void writeJSON(HttpServletRequest request,HttpServletResponse response,ObjectModel objModel) throws UnsupportedEncodingException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		JSON obj = null;
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			obj = (JSON) JSON.toJSON(objModel);
		} catch (IOException e) {
			objModel.setFlat(1);
			objModel.setDesc("网络异常!");
			objModel.setObj("");
			obj = (JSON) JSON.toJSON(objModel);
			e.printStackTrace();
		}
		
		String callback = Parameters.getStringParameter(request, "callback");
		System.out.println("callback有值表示js跨域调用了");
		System.out.println("callback:"+callback);

		if(callback != null){
			pw.println(obj);
		}else{
			pw.print(obj);
		}
		pw.flush();
		pw.close();
	}
	
	
	/**
	 * 接口返回无路径信息,带参数集
	 * 修改者名字   xxy
	 * 修改日期   2014-7-26
	 * 修改内容
	 * @param @param request
	 * @param @param response
	 * @param @param objModel	ObjectModel集合类
	 * @param @param callback	jquery数据
	 * @param @throws UnsupportedEncodingException
	 * @return void
	 */
	public static void writeJSON_jQuery(HttpServletRequest request,HttpServletResponse response,ObjectModel objModel,String callback) throws UnsupportedEncodingException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		JSON obj = null;
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			obj = (JSON) JSON.toJSON(objModel);
		} catch (IOException e) {
			objModel.setFlat(1);
			objModel.setDesc("网络异常!");
			objModel.setObj("");
			obj = (JSON) JSON.toJSON(objModel);
			e.printStackTrace();
		}
		
		System.out.println("callback有值表示js跨域调用了");
		System.out.println("callback:"+callback);
		
		if(callback != null){
			pw.println(obj);
		}else{
			pw.print(obj);
		}
		pw.flush();
		pw.close();
	}
	
	
	/**
	 * 接口返回无路径信息,带参数集，返回DataGrid表格使用
	 * 修改者名字   xxy
	 * 修改日期   2014-8-27
	 * 修改内容
	 * @param @param request
	 * @param @param response
	 * @param @param page
	 * @param @throws IOException
	 * @return void
	 */
	public static void writeJSON(HttpServletRequest request,HttpServletResponse response,DataGridModel dataGrid) throws IOException{
		CharsetUtil.ToUTF8(request, response);
		JSON obj = null;
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			obj = (JSON) JSON.toJSON(dataGrid);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.println(obj);
		pw.flush();
		pw.close();
	}
	
	/**
	 * 接口返回无路径信息,带参数集，带时间格式，返回DataGrid表格使用
	 * 修改者名字   xxy
	 * 修改日期   2014-8-27
	 * 修改内容
	 * @param @param request
	 * @param @param response
	 * @param @param page
	 * @param @throws IOException
	 * @return void
	 */
	public static void writeJSON(HttpServletRequest request,HttpServletResponse response,DataGridModel dataGrid,String dateFormat) throws IOException{
		CharsetUtil.ToUTF8(request, response);
		JSON obj = null;
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			obj = (JSON) JSON.toJSON(dataGrid);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.write(JSONObject.toJSONStringWithDateFormat(obj,dateFormat));
		pw.flush();
		pw.close();	
	}
	
	/**
	 * 接口返回带路径信息,带参数集
	 * 修改者名字   xxy
	 * 修改日期   2014-7-26
	 * 修改内容
	 * @param @param request
	 * @param @param response
	 * @param @param objModel	ObjectModel集合类
	 * @param @param strURL	需要返回到哪个路径
	 * @param @throws UnsupportedEncodingException
	 * @return void
	 */
	public static void writeJSON(HttpServletRequest request,HttpServletResponse response,ObjectModel objModel,String strURL) throws UnsupportedEncodingException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); 
		
		Object obj = null;
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			obj = JSON.toJSON(objModel);
		} catch (IOException e) {
			objModel.setFlat(1);
			objModel.setDesc("网络异常!");
			objModel.setObj("");
			obj = JSON.toJSON(objModel);
			e.printStackTrace();
		}
		String callback = Parameters.getStringParameter(request, "callback");
		if(callback != null){
			pw.println(JSON.toJSONStringWithDateFormat(obj,"yyyy-MM-dd HH:mm:ss"));
		}else{
			try {
				request.getRequestDispatcher(SmallMethods.getProperties("jsp")+strURL).forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pw.println(JSON.toJSONStringWithDateFormat(obj,"yyyy-MM-dd HH:mm:ss"));
		}
		pw.flush();
		pw.close();
	}
	
	/**
	 * 接口返回带路径信息,不带参数集
	 * 修改者名字   xxy
	 * 修改日期   2014-7-29
	 * 修改内容
	 * @param @param request
	 * @param @param response
	 * @param @param strURL
	 * @param @throws UnsupportedEncodingException
	 * @return void
	 * @throws IOException 
	 */
	public static void writeJSON(HttpServletRequest request,HttpServletResponse response,String strURL) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		try {
			request.getRequestDispatcher(SmallMethods.getProperties("jsp")+strURL).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
