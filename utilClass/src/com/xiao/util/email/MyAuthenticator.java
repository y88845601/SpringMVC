package com.xiao.util.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 验证邮箱身份信息
 * <p>Title: MyAuthenticator.java</p>
 * @author xxy 
 * @date 2015-3-18 下午2:46:50 
 * @version V1.0
 */
public class MyAuthenticator extends Authenticator {
	
	String userName = null;
	String password = null;
	
	public MyAuthenticator(){}
	
	public MyAuthenticator(String userName,String password){
		this.userName = userName;
		this.password = password;
	}
	
	protected PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(userName, password);
	}
}
