package com.xiao.util.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 邮件发送格式配置
 * <p>Title: SimpleMailSender.java</p>
 * @author xxy 
 * @date 2015-3-17 下午3:21:36 
 * @version V1.0
 */
public class SimpleMailSender {
	
	public static boolean sendHtmlMail(MailConfig mailconfig){
		//设置邮件发送的服务器(公共参数)
		//发件人邮箱账号
		//发件人邮箱密码
		//发件人邮箱账号
		
		//判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties Pro = mailconfig.getProperties();
		//如果需要身份认证，则创建一个密码验证器
		if(mailconfig.isValidate()){
			System.out.println("开始身份验证");
			authenticator = new MyAuthenticator(mailconfig.getUserName(),mailconfig.getPassword());
		}
		//根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(Pro, authenticator);
		try {
			//根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			//创建邮件发送者地址
			Address from = new InternetAddress(mailconfig.getFromAddress());
			//设置邮件消息的发送者
			mailMessage.setFrom(from);
			//创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailconfig.getToAddress());
			//Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			//设置邮件消息的主题
			mailMessage.setSubject(mailconfig.getSubject());
			//设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			//MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			//创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			//设置HTML内容
			html.setContent(mailconfig.getContent(), "text/html;charset=utf-8");
			mainPart.addBodyPart(html);
			//将MinMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			//发送邮件
			Transport.send(mailMessage);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e){
			e.printStackTrace();
		}
		return true;
	}

}
