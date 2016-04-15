package com.xxy.demo.bean;

import java.util.Date;

public class TUser {
	
	private String id;
	private Long mobile;
	private String passwd;
	private String name;
	private Long qqno;
	private String wxno;
	private Date creattime;
	private Integer state;
	private Integer type;
		
	public TUser() {}
	
	public TUser(String id, Long mobile, String passwd, String name, Date creattime) {
		this.id = id;
		this.mobile = mobile;
		this.passwd = passwd;
		this.name = name;
		this.creattime = creattime;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getQqno() {
		return qqno;
	}
	public void setQqno(Long qqno) {
		this.qqno = qqno;
	}
	public String getWxno() {
		return wxno;
	}
	public void setWxno(String wxno) {
		this.wxno = wxno;
	}
	public Date getCreattime() {
		return creattime;
	}
	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
