package com.xiao.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.input.SAXBuilder;

/**
 * 解析XML的公共方法
 * <p>Title: XMLUtil.java</p>
 * @author xxy 
 * @date 2015-7-1 下午4:56:12 
 * @version V1.0
 */
public class XMLUtil {
	
	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * 修改者名字   xxy
	 * 修改日期   2015-7-1
	 * 修改内容
	 * @param @param strxml
	 * @param @return
	 * @return Map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map doXMLParse(String strxml) {
		System.out.println("----------------------------------- 解析XML数据 begin -----------------------------------");
		Map m = new HashMap();
		InputStream in = null;
		System.out.println("接收到的参数："+strxml);
		try {
			strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
			if (null == strxml || "".equals(strxml)) {
				System.out.println("----------------------------------- 解析XML数据 end -----------------------------------");
				return null;
			}
			in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
			SAXBuilder builder = new SAXBuilder();
			org.jdom.Document doc = builder.build(in);
			org.jdom.Element root = doc.getRootElement();
			List list = root.getChildren();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				org.jdom.Element e = (org.jdom.Element) it.next();
				String k = e.getName();
				String v = "";
				List children = e.getChildren();
				//	判断是否是一级子节点的元素键值对
				if (children.isEmpty()) {
					//	一级子节点的元素键值对
					v = ((org.jdom.Element) e).getTextNormalize();
					//System.out.println(k+":"+v);
					m.put(k, v);
				}
				else {
					//	该值含有二级子节点的键值对,重新调用该方法获取键值对
					v = XMLUtil.getChildrenText(children);
					//System.out.println("出现二级子节点:"+v);
					Map m2 = new HashMap();
					m2 = doXMLParse("<xml>"+v+"</xml>");
					m.putAll(m2);
				}
			}
		}
		catch (Exception e) {
			System.out.println("解析XML报文错误");
		}
		finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (IOException e1) {
					in = null;
				}
			}
		}
		System.out.println("----------------------------------- 解析XML数据 end -----------------------------------");
		return m;
	}

	/**
	 * 辅助doXMLParse类,获取value数据
	 * 修改者名字   xxy
	 * 修改日期   2015-7-1
	 * 修改内容
	 * @param @param children
	 * @param @return
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				org.jdom.Element e = (org.jdom.Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(XMLUtil.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		return sb.toString();
	}

}
