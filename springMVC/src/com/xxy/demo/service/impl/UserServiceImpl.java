package com.xxy.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxy.demo.bean.TUser;
import com.xxy.demo.dao.IUserDao;
import com.xxy.demo.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private IUserDao userDao;
	
	@Override
	public int findTUserByPwd(Long mobile, String pwd) {
		log.debug("this is findTUser service");
		
		TUser tu = userDao.findTUserByPwd(mobile, pwd);
		if(tu == null){
			return 1;//账号、密码错误
		}
		return 0;
	}

}
