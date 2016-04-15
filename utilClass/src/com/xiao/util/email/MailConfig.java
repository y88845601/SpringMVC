package com.xiao.util.email;

import java.util.Properties;

/**
 * 邮件发送的相关参数配置
 * <p>Title: MailConfig.java</p>
 * @author xxy 
 * @date 2015-3-17 下午2:35:04 
 * @version V1.0
 */
public class MailConfig {
	
	//登录邮件发送服务器的账号、密码
	private String userName;
	private String password;
	//发送邮件的IP
	private String mailServiceHost;
	//发送邮件的端口
	private String mailServiceProt = "25";
	//邮件发送者的地址
	private String fromAddress;
	//邮件接收者的地址
	private String toAddress;
	//是否需要身份验证
	private boolean validate = false;
	//邮件主题
	private String subject;
	//邮件文本内容
	private String content;
	//邮件附件的文件名
	private String[] attachFileNames;
	
	/**
	 * 获取邮件会话属性
	 * 修改者名字   xxy
	 * 修改日期   2015-3-17
	 * 修改内容
	 * @param @return
	 * @return Properties
	 */
	public Properties getProperties(){
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServiceHost);
		p.put("mail.smtp.port", this.mailServiceProt);
		p.put("mail.smtp.auth", validate ? "true":"false");
		return p;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMailServiceHost() {
		return mailServiceHost;
	}
	public void setMailServiceHost(String mailServiceHost) {
		this.mailServiceHost = mailServiceHost;
	}
	public String getMailServiceProt() {
		return mailServiceProt;
	}
	public void setMailServiceProt(String mailServiceProt) {
		this.mailServiceProt = mailServiceProt;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public boolean isValidate() {
		return validate;
	}
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getAttachFileNames() {
		return attachFileNames;
	}
	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}

}
