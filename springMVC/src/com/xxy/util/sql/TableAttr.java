package com.xxy.util.sql;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xxy.demo.bean.TUser;
import com.xxy.util.DateUtil;
import com.xxy.util.SmallMethods;




/**
 * SQL语句处理<br>
 * 使用该方法必须保证数据库字段名与bean中的变量名一致
 * @author xingyuan
 * @date 2016-3-7
 * <!------------------>
 */
public class TableAttr {
	
	/**
	 * 生成系统中的update语句,自定义where条件
	 * <br>程序中调用该接口,方便外部调用
	 * @author xingyuan
	 * @date 2016-3-9
	 * <!------------------>
	 * @param cla 要修改的库表class
	 * @param obj 要修改的bean实体
	 * @param str 自定义条件的字段
	 * @return String
	 */
	public static String getUpdateSql(Class<?> cla,Object obj,String str){
		String s = null;
		try {
			s = getMySqlUpdateSql(cla, obj, str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/**
	 * 生成系统中的update语句,默认where条件为 ID
	 * <br>程序中调用该接口,方便外部调用
	 * @author xingyuan
	 * @date 2016-3-9
	 * <!------------------>
	 * @param cla 要修改的库表class
	 * @param obj 要修改的bean实体
	 * @return String
	 */
	public static String getUpdateSql(Class<?> cla,Object obj){
		String s = null;
		try {
			s = getMySqlUpdateSql(cla, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/**
	 * 生成系统中的update语句,自定义where条件
	 * <br>程序中调用该接口,方便外部调用
	 * @author xingyuan
	 * @date 2016-3-9
	 * <!------------------>
	 * @param cla 要修改的库表class
	 * @param obj 要修改的bean实体
	 * @param whstr 自定义条件的字段集合
	 * @return String
	 */
	public static String getUpdateSql(Class<?> cla,Object obj,String[] whstr){
		String s = null;
		try {
			s = getMySqlUpdateSql(cla, obj,whstr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/**
	 * 生成系统中的insert语句
	 * <br>程序中调用该接口,方便外部调用
	 * @author xingyuan
	 * @date 2016-3-9
	 * <!------------------>
	 * @param cla 要插入的库表class
	 * @param obj 要插入的bean实体
	 * @return String
	 */
	public static String getInsertSql(Class<?> cla,Object obj){
		String str = null;
		try{
			str = getMySqlAddSql(cla, obj);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	//=====================================================
	
	/*public static String getMySqlSelectSql(){
		
		return "";
	}*/
	
	/**
	 * 生成MySql的update语句,自定义where条件
	 * @author xingyuan
	 * @date 2016-3-9
	 * <!------------------>
	 * @param cla 要修改的库表class
	 * @param obj 要修改的bean实体
	 * @param str 自定义条件的字段
	 * @return
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException String
	 */
	public static String getMySqlUpdateSql(Class<?> cla,Object obj,String str) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		return getUpdateSql(cla, obj, new String[]{str});
	}
	
	/**
	 * 生成MySql的update语句,自定义where条件
	 * @author xingyuan
	 * @date 2016-3-9
	 * <!------------------>
	 * @param cla 要修改的库表class
	 * @param obj 要修改的bean实体
	 * @param whstr 自定义条件的字段集合
	 * @return
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * String
	 */
	public static String getMySqlUpdateSql(Class<?> cla,Object obj,String[] whstr) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		StringBuffer sb = new StringBuffer();
		
		List<String> l = BeanControl.getAttrList(cla);
		Map<String,Object> map = BeanControl.getClaMap(cla, obj, l);
		
		StringBuffer w = new StringBuffer();//where 条件
		
		for(String str : l){
			String sname = str.substring(0, str.indexOf("-"));
			String stype = str.substring(str.indexOf("-")+1, str.length());
			
			//对数据进行处理,拼接sql语句
			Object val = map.get(sname);
			if(val != null){
				sb.append(sname.toUpperCase() + " = ");
				if("String".equals(stype)){
					sb.append("'"+String.valueOf(val) + "',");
				}else if("Long".equals(stype)){
					sb.append((Long)val + ",");
				}else if("Integer".equals(stype)){
					sb.append((Integer)val + ",");
				}else if("Date".equals(stype)){
					sb.append("'"+DateUtil.dateToStr((Date)val, "yyyy-MM-dd HH:mm:ss")+"',");
				}else{
					sb.append("'"+String.valueOf(val) + "',");
				}
				//where条件
				if(Arrays.asList(whstr).contains(sname)){
					w.append(sname.toUpperCase()+" = ");
					if("String".equals(stype)){
						w.append("'"+String.valueOf(val) + "'");
					}else if("Long".equals(stype)){
						w.append((Long)val);
					}else if("Integer".equals(stype)){
						w.append((Integer)val);
					}else if("Date".equals(stype)){
						w.append("'"+DateUtil.dateToStr((Date)val, "yyyy-MM-dd HH:mm:ss")+"'");
					}else{
						w.append("'"+String.valueOf(val) + "'");
					}
					w.append(" AND ");
				}
			}
		}
		System.out.println("set:"+sb);
		System.out.println("where:"+w);
		String s = "UPDATE " + TableName.getTableName(obj.getClass()) + " SET "+SmallMethods.ridLastOne(sb.toString()) +" WHERE " + SmallMethods.ridLastOne(w.toString(), 5);
		return s;
	}
	
	/**
	 * 生成MySql的update语句,默认where条件为 ID
	 * @author xingyuan
	 * @date 2016-3-9
	 * <!------------------>
	 * @param cla 要修改的库表class
	 * @param obj 要修改的bean实体
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException String
	 */
	public static String getMySqlUpdateSql(Class<?> cla,Object obj) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		return getUpdateSql(cla, obj, new String[]{"ID"});
	}
	
	/**
	 * 生成MySql的insert语句
	 * @author xingyuan
	 * @date 2016-3-9
	 * <!------------------>
	 * @param cla 要插入的库表class
	 * @param obj 要插入的bean实体
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException String
	 */
	public static String getMySqlAddSql(Class<?> cla,Object obj) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		StringBuffer sbkey = new StringBuffer();
		StringBuffer sbval = new StringBuffer();
		
		List<String> l = BeanControl.getAttrList(cla);//拿到bean中所有私有参数的名字及类型
		Map<String,Object> map = BeanControl.getClaMap(cla, obj,l);//拿到bean中所有私有参数的名字与值
		for(String str : l){
			String sname = str.substring(0, str.indexOf("-"));
			String stype = str.substring(str.indexOf("-")+1, str.length());
			Object val = map.get(sname);
			
			if(val != null){
				sbkey.append(sname.toUpperCase() + ",");
				if("String".equals(stype)){
					sbval.append("'"+String.valueOf(val) + "',");
				}else if("Long".equals(stype)){
					sbval.append((Long)val + ",");
				}else if("Integer".equals(stype)){
					sbval.append((Integer)val + ",");
				}else if("Date".equals(stype)){
					sbval.append("'"+DateUtil.dateToStr((Date)val, "yyyy-MM-dd HH:mm:ss")+"',");
				}else{
					sbval.append(String.valueOf(val) + ",");
				}
			}
		}
		System.out.println("sbkey:"+sbkey);
		System.out.println("sbval:"+sbval);
		String s = "INSERT INTO " + TableName.getTableName(obj.getClass()) + "("+SmallMethods.ridLastOne(sbkey.toString())+") VALUES ("+SmallMethods.ridLastOne(sbval.toString())+")";
		return s;
	}
	
	public static void main(String [] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		TUser tuser = new TUser("as|d4-s90df,5545as", 22353467L, "dfa5d2189301ehaddad9", "34asd", new Date());
		tuser.setWxno("y88845601");
		List<String> l = BeanControl.getAttrList(tuser.getClass());
		for(String s : l){
			System.out.println(s);
		}
		Object obj = tuser;
		System.out.println("----------------");
		/*String sb = getMySqlUpdateSql(tuser.getClass(),obj);
		System.out.println(sb);
		System.out.println(getMySqlAddSql(tuser.getClass(), obj));
		System.out.println(getUpdateSql(tuser.getClass(), obj,"iD"));
		System.out.println(getMySqlUpdateSql(tuser.getClass(), obj,new String[]{"mobile","id"}));
		System.out.println(getInsertSql(tuser.getClass(), obj));
		System.out.println(getUpdateSql(tuser.getClass(), obj));
		System.out.println(getMySqlUpdateSql(tuser.getClass(),obj));
		System.out.println(getUpdateSql(tuser.getClass(), obj,"id"));
		*/
		System.out.println(getUpdateSql(tuser.getClass(), obj,new String[]{"mobile","id"}));
	}
	
}
