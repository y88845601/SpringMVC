package com.xxy.demo.service;


public interface IUserService {
	
	/**
	 * 根据手机号、密码查询会员信息
	 * @author xingyuan
	 * @date 2016-3-7
	 *-----------------------
	 * @param mobile 登录手机号
	 * @param pwd 登录密码
	 * @return
	 */
	public int findTUserByPwd(Long mobile,String pwd);

}
