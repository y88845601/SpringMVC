package com.xxy.demo.dao.provider;

import java.util.Map;

import org.apache.log4j.Logger;

public class UserDaoProvider {
	
	private static final Logger log = Logger.getLogger(UserDaoProvider.class);
	
	/**
	 * 返回根据手机号、密码查询会员信息的sql
	 * @author xingyuan
	 * @date 2016-3-7
	 * <!------------------>
	 * @param mobile 登录手机号
	 * @param pwd 登录密码
	 * @return
	 */
	public String getTUserByPwd(Map<String,Object> map){
		Long mobile = Long.parseLong(map.get("mobile").toString());
		String pwd = map.get("pwd").toString().trim();
		
		String str = "SELECT * FROM T_USER T WHERE T.MOBILE = "+mobile +" AND T.PASSWD = '"+pwd+"'";
		log.debug("getTUserByPwd sql:"+str);
		
		return str;
	}
	
	/**
	 * 返回根据ID获取会员信息的sql
	 * @author xingyuan
	 * @date 2016-3-7
	 * <!------------------>
	 * @param id
	 * @return
	 */
	public String getTUserById(String id){
		String str = "SELECT * FROM T_USER T WHERE T.ID = '"+id.trim()+"'";
		log.debug("getTUserById sql:"+str);
		return str;
	}

}
