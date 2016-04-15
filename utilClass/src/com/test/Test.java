package com.test;

import com.xiao.util.email.MailConfig;
import com.xiao.util.email.SimpleMailSender;

public class Test {
	
	@SuppressWarnings("static-access")
	public static void main(String [] args){
		MailConfig mailconfig = new MailConfig();
		mailconfig.setUserName("xiaoxingyuan@lang360.com");
		mailconfig.setPassword("xiao2014");
		
		mailconfig.setMailServiceHost("113.108.16.44");
		mailconfig.setMailServiceProt("25");
		mailconfig.setFromAddress("xiaoxingyuan@lang360.com");
		mailconfig.setToAddress("log@lang360.com");
		
		mailconfig.setSubject("测试");//主题
		mailconfig.setContent("由测试程序发出");//内容
		
		SimpleMailSender simpleMailSender = new SimpleMailSender();
		simpleMailSender.sendHtmlMail(mailconfig);
	}

}
