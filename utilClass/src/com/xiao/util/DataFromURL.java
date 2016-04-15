package com.xiao.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 跨域、跨应用程序调用
 * <p>Title: DataFromURL.java</p>
 * @author xxy 
 * @date 2014-5-28 下午6:21:56 
 * @version V1.0
 */
public class DataFromURL {
	
	public static void main(String [] args){
		
		String filepath = "E:\\images\\1_1.jpg";
		String strURL = "http://192.168.1.50:8080/photoServer/uploadServlet";
		Map<String, Object> textParam = new HashMap<String, Object>();
		textParam.put("method", "2");
		textParam.put("userid", "1065");
		Map<String, Object> fileParam = new HashMap<String, Object>();
		fileParam.put("userfile", filepath);
		
		try {
			String str = DataFromURL.getFormUpload(strURL, textParam, fileParam);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件跨域上传实现方法
	 * @author xingyuan
	 * @date 2016-3-18
	 * <!------------------>
	 * @param strURL 请求地址
	 * @param textParam 请求的文本信息Map
	 * @param fileParam 请求的文本信息Map
	 * @return
	 * @throws IOException
	 */
	public static String getFormUpload(String strURL,Map<String,Object> textParam,Map<String,Object> fileParam) throws IOException{
		String res = "";
		String BOUNDARY = "---------------------------"; //boundary就是request头和上传文件内容的分隔符
		
		//组织请求路径
		URL url = new URL(strURL.trim());
		//头文件信息准备
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		System.out.println("connection OK !");
		
		conn.setDoInput(true);// 设置是否从httpUrlConnection读入，默认情况下是true
		conn.setDoOutput(true);// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在  http正文内，因此需要设为true, 默认情况下是false
		conn.setAllowUserInteraction(true);//如果为 true，则在允许用户交互（例如弹出一个验证对话框）的上下文中对此 URL 进行检查。
		conn.setIfModifiedSince(0);//
		conn.setUseCaches(false);//是否允许使用缓存 Post 请求不能使用缓存
		
		conn.setRequestMethod("POST");//设置请求方式
		//conn.setRequestProperty("Contrnt-Type", "application/x-www-form-urlencoded");// 设定传送的内容类型是可序列化的java对象
		conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		
		//conn.setRequestProperty("Connection", "Keep-Alive");
		//conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
		//conn.setConnectTimeout(5000);
		//conn.setReadTimeout(30000);
		
		System.out.println("connection header OK ! ");
		
		OutputStream  out = new DataOutputStream(conn.getOutputStream());
		
		//text 文本处理 
		if(textParam != null){
			StringBuffer strBuf = new StringBuffer();
			
			Iterator<Map.Entry<String, Object>> iter = textParam.entrySet().iterator();
			while(iter.hasNext()){
				Map.Entry<String, Object> entry = iter.next();
				String inputName = entry.getKey();
				String inputValue = entry.getValue().toString();
				if(inputValue == null){
					System.out.println("text value is null");
					continue;
				}
				strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
				strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
				strBuf.append(inputValue);
			}
			out.write(strBuf.toString().getBytes());
		}
		
		//file 文件处理
		if(fileParam != null){
			Iterator<Map.Entry<String, Object>> iter = fileParam.entrySet().iterator();  
			while(iter.hasNext()){
				Map.Entry<String, Object> entry = iter.next();
				String inputName = entry.getKey();
				String inputValue = entry.getValue().toString();
				
				if(inputValue == null){
					System.out.println("image is null");
					continue;
				}
				
				File file = new File(inputValue);
				String filename = file.getName();
				//MagicMatch match = Magic.getMagicMatch(file, false, true);
				//String contentType = match.getMimeType();
				String contentType = filename.substring(filename.lastIndexOf("."));
				
				StringBuffer strBuf = new StringBuffer();
				strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
				strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename + "\"\r\n");
				strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
				
				out.write(strBuf.toString().getBytes());
				
				DataInputStream in = new DataInputStream(new FileInputStream(file));
				
				int bytes = 0;
				byte[] bufferOut = new byte[1024];
				while ((bytes = in.read(bufferOut)) != -1){
					out.write(bufferOut, 0, bytes);
				}
				in.close();
			}
		}
		
		byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
		out.write(endData);
		out.flush();
		out.close();
		
		// 读取返回数据    
		StringBuffer strBuf = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line = null;
		while ((line = reader.readLine()) != null) {
		    strBuf.append(line).append("\n");
		}
		res = strBuf.toString();
		reader.close();
		reader = null;
		return res;
	}
	
	/**
	 * 跨域、跨应用访问调用
	 * 修改者名字   xxy
	 * 修改日期   2014-5-28
	 * 修改内容
	 * @param @param strURL	访问地址
	 * @param @param param	传递参数集合
	 * @return String
	 */
	public static String getDataFromURL(String strURL,Map<String,Object> param) throws Exception{
		
		URL url = new URL(strURL.trim());
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		System.out.println("Connetion ok!");
		System.out.println("getData begin!");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestMethod("POST");
		
//		URLConnection conn = url.openConnection();
//		conn.setDoInput(true);
		
		OutputStreamWriter write = new OutputStreamWriter(conn.getOutputStream());
		final StringBuilder sb;
		if(param != null){
			sb = new StringBuilder(param.size() << 4);	//	4次方
			final Set<String> keys = param.keySet();
			for(final String key : keys){
				final Object value = param.get(key);
				sb.append(key);	//	不能包含特殊字符
				sb.append('=');
				sb.append(value);
				sb.append('&');
			}
			sb.deleteCharAt(sb.length() - 1);
		}else{
			System.out.println("no param!");
			sb = new StringBuilder(100 << 4);
		}
		write.write(sb.toString());
		write.flush();
		write.close();
		
		InputStreamReader reder = new InputStreamReader(conn.getInputStream(),"UTF-8");
		BufferedReader breader = new BufferedReader(reder);
		
		String content = "";
		String result = "";
		while((content = breader.readLine()) != null){
			result += content;
		}
		System.out.println("getData end!");
		return result;
	}
	
	
	/**
	 * 带请求格式跨域、跨系统调用
	 * 修改者名字   xxy
	 * 修改日期   2014-6-3
	 * 修改内容
	 * @param @param strURL	访问路径
	 * @param @param param	访问参数(一个值)
	 * @param @param length	值的长度
	 * @param @param ctype	Content-Type值
	 * @return String
	 */
	public static String getICBCDataFromURLQM(String strURL,String param,int length,String ctype) throws Exception{
		
		URL url = new URL(strURL);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		System.out.println("Connetion ok!");     
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		conn.setRequestProperty("Content-Type", ctype);
		conn.setRequestMethod("POST");
		
		conn.setRequestProperty("Content-Length", length+"");
		
//		URLConnection conn = url.openConnection();
//		conn.setDoInput(true);
		
		OutputStreamWriter write = new OutputStreamWriter(conn.getOutputStream());
//		if(param != null){
//			sb = new StringBuilder(param.size() << 4);	//	4次方
//			final Set<String> keys = param.keySet();
//			for(final String key : keys){
//				final Object value = param.get(key);
//				sb.append(key);	//	不能包含特殊字符
//				sb.append('=');
//				sb.append(value);
//				sb.append('&');
//			}
//			sb.deleteCharAt(sb.length() - 1);
//		}else{
//			System.out.println("no param!");
//			sb = new StringBuilder(100 << 4);
//		}
		write.write(param);
		write.flush();
		write.close();
		
		InputStreamReader reder = new InputStreamReader(conn.getInputStream(),"GB2312");
		BufferedReader breader = new BufferedReader(reder);
		
		String content = "";
		String result = "";
		while((content = breader.readLine()) != null){
			result += content;
		}
		return result;
	}
	/**
	 * 带请求格式跨域、跨系统调用
	 * 修改者名字   xxy
	 * 修改日期   2014-6-3
	 * 修改内容
	 * @param @param strURL	访问路径
	 * @param @param param	访问参数(一个值)
	 * @param @param length	值的长度
	 * @param @param ctype	Content-Type值
	 * @param @param codingFormat	编码格式
	 * @return String
	 */
	public static String getICBCDataFromURLQM(String strURL,String param,int length,String ctype,String codingFormat) throws Exception{
		
		URL url = new URL(strURL);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		System.out.println("Connetion ok!");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		conn.setRequestProperty("Content-Type", ctype);
		conn.setRequestMethod("POST");
		
		conn.setRequestProperty("Content-Length", length+"");

		OutputStreamWriter write = new OutputStreamWriter(conn.getOutputStream());

		write.write(param);
		write.flush();
		write.close();
		
		InputStreamReader reder = new InputStreamReader(conn.getInputStream(),codingFormat);
		BufferedReader breader = new BufferedReader(reder);
		
		String content = "";
		String result = "";
		while((content = breader.readLine()) != null){
			result += content;
		}
		return result;
	}

}
