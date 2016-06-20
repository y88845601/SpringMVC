package com.xxy.util;

/**
 * 小方法集
 * @author xingyuan
 * @date 2016-3-8
 * <!------------------>
 */
public class SmallMethods {
	
	/**
	 * 获取项目的根目录
	 * 修改者名字   xxy
	 * 修改日期   2016-4-11
	 * 修改内容
	 * @param @param request
	 * @param @return
	 * @return String
	 */
	public static String getRootPath(HttpServletRequest request){
		return request.getSession().getServletContext().getRealPath("");
	}
	
	/**
	 * 获取请求者的IP
	 * 修改者名字   xxy
	 * 修改日期   2016-4-11
	 * 修改内容
	 * @param @param request
	 * @param @return
	 * @return String
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 根据IP获取MAC地址(内网测试通过,外网未测试,不稳定尽量不要使用)
	 * 修改者名字   xxy
	 * 修改日期   2016-4-12
	 * 修改内容
	 * @param @param ip
	 * @param @return
	 * @return String
	 */
	public static String getMACAddress(String ip) {
		String str = "";
		String macAddress = "";
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(
								str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return macAddress;
	}
	
	/**
	 * 从API配置文件中拿到对应的参数值
	 * 修改者名字   xxy
	 * 修改日期   2016-4-11
	 * 修改内容
	 * @param @param key
	 * @param @return
	 * @return String
	 */
	public static String getProperties(String key){
		String val = null;
		Properties property = new Properties();
		try {
			String rootUrl = SmallMethods.class.getClassLoader().getResource("").getPath();
			//Constants.propertiesURL为properties文件储存的路径
			property.load(new FileInputStream(rootUrl+Constants.propertiesURL));//拿到API配置文件
			val = property.getProperty(key);//从配置文件中拿到对应key的值
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}
	
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
