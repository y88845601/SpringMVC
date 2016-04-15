package com.xxy.util.sql;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据库表表明与bean的对应关系
 * @author xingyuan
 * @date 2016-3-7
 * <!------------------>
 */
public class TableName {
	
	/**
	 * 根据bean拿到bean对应的数据库表的表名
	 * @author xingyuan
	 * @date 2016-3-7
	 * <!------------------>
	 * @param tableBean
	 * @return String
	 */
	public static String getTableName(Class<?> tableBean){
		return tableMap().get(tableBean.getSimpleName());
	}
	
	/**
	 * 数据库表表明与bean的对应关系<br>
	 * key-bean的名字  val-库表表名
	 * @author xingyuan
	 * @date 2016-3-7
	 * <!------------------>
	 * @return
	 */
	public static Map<String,String> tableMap(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("TUser", "T_USER");
		return map;
	}

}
