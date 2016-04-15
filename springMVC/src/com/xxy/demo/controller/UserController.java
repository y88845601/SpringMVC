package com.xxy.demo.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xxy.demo.service.IUserService;
import com.xxy.util.demo.BasisFileUtil;
import com.xxy.util.http.DataFromURL;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/login")
	public String Login(Model model,@RequestParam("username")Long username,@RequestParam("pwd")String pwd){
		log.debug("username:"+username);
		log.debug("pwd:"+pwd);
		
		int a = userService.findTUserByPwd(username, pwd);
		log.debug("a:"+a);
		model.addAttribute("aa", a);//返回页面值
		return "userInfor";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/upload")
	public void upload(HttpServletRequest request,HttpServletResponse response){
		log.debug("file upload begin");
		String method = null;
		String userid = null;
		String filePath = null;
		String path = "C:\\ehcache\\linlang\\images\\";//保存在本地的路径
		String url = "http://192.168.1.128:8080/photoServer/uploadServlet";//请求接口的文件
		
		Map<String, Object> textParam = new HashMap<String, Object>();
		Map<String, Object> fileParam = new HashMap<String, Object>();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<Object> items = upload.parseRequest(request);
			Iterator<Object> it = items.iterator();
			while (it.hasNext()) {
				FileItem item = (FileItem) it.next();
				if (item.isFormField()) {
					if (item.getFieldName().toLowerCase().equals("method")) {
						method = item.getString("UTF-8").trim();
						continue;
					}
					if (item.getFieldName().toLowerCase().equals("userid")) {
						userid = item.getString("UTF-8").trim();
						continue;
					}
				}
			}
			
			log.debug("得到的method:"+method+",userid:"+userid);

			filePath = BasisFileUtil.uploadFile(items, path);
			
			textParam.put("method", method);
			textParam.put("userid", userid);
			
			fileParam.put("userfile", filePath);
			
			String str = DataFromURL.getFormUpload(url, textParam, fileParam);
			log.debug(str);
			
			PrintWriter pw = response.getWriter();
			pw.print(JSON.toJSON(str));
			pw.flush();
			pw.close();
			
			Map<String,Object> map = JSONObject.parseObject(str);
			Integer falt = StringUtils.isBlank(map.get("flat")+"") ? null : (Integer) map.get("flat");
			
			if(falt == 0){
				//上传成功后删除文件
				try{
					BasisFileUtil.deleteFile(filePath);
					log.debug("delete file OK");
				}catch(Exception e){
					log.debug("delete file error,file path:"+filePath);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
