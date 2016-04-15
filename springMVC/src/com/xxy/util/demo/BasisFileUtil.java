package com.xxy.util.demo;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;

import com.xiao.util.RandomNumber;

/**
 * 基础方法类
 * @author xingyuan
 * @date 2016-3-21
 * <!------------------>
 */
public class BasisFileUtil {
	
	/**
	 * 根据路径删除指定的目录或文件，无论存在与否
	 * @author xingyuan
	 * @date 2016-3-21
	 * <!------------------>
	 * @param sPath
	 * @return boolean
	 */
	public static boolean DeleteFolder(String sPath){
		boolean flag = false;
		File file = new File(sPath);
		//判断目录是否存在
		if(!file.exists()){
			return flag;
		}else{
			//判断是否为文件
			if (file.isFile()){
				return deleteFile(sPath);
			}else{
				return deleteDirectory(sPath);
			}
		}
	}
	
	/**
	 * 删除单个文件
	 * @author xingyuan
	 * @date 2016-3-21
	 * <!------------------>
	 * @param sPath 要删除文件的路径
	 * @return boolean
	 */
	public static boolean deleteFile(String sPath){
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if(file.isFile() && file.exists()){
			file.delete();
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * @author xingyuan
	 * @date 2016-3-21
	 * <!------------------>
	 * @param sPath 要删除文件件夹的路径
	 * @return boolean
	 */
	public static boolean deleteDirectory(String sPath){
		
		//如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		
		File dirFile = new File(sPath);
		boolean flag = false;
		
		//如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		
		//删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				//删除子文件
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) break;
			}else{
				//删除子目录
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) break;
			}
		}
		if (!flag) return false;
		//删除当前目录
		if (dirFile.delete()) {
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 上传文件保存文件方法
	 * <br>解决跨域上传图片 将图片先写入本地文件,再从本地上传至图片服务器
	 * <br>缺点：图片经过两次读写及服务器之间的传输,效率较慢
	 * @author xingyuan
	 * @date 2016-3-21
	 * <!------------------>
	 * @param items 请求的上传图片的信息 
	 * <br>ServletFileUpload upload = new ServletFileUpload(factory);
	 * <br>List<Object> items = upload.parseRequest(request);
	 * @param path 保存本地的路径
	 * @param filefastname 文件的名字 上传文件名字则不再自动生成
	 * @return
	 * @throws Exception String
	 */
	public static String uploadFile(List<Object> items,String path,String filefastname) throws Exception{
		//解决跨域上传图片 将图片先写入本地文件,再从本地上传至图片服务器
		//缺点：图片经过两次读写及服务器之间的传输,效率较慢
		String filePath = "";
		
		Iterator<Object> itr = items.iterator();
		
		while (itr.hasNext()){
			FileItem item = (FileItem) itr.next();
			if (!item.isFormField()) {
				if (StringUtils.isNotBlank(item.getName())){
					String fileName = item.getName();
					
					if (!new File(path).isDirectory()) {
						new File(path).mkdirs();
					}
					
					// 随机生成一个文件名
					filefastname = StringUtils.isNotBlank(filefastname.trim()) ? filefastname : RandomNumber.DateRandomNumber("ddHHmmssSSS", 7);
					String fn = filefastname+ fileName.substring(fileName.lastIndexOf("."));
					
					File file = new File(path, fn);
					filePath = path+fn;
					item.write(file);
				}
			}
		}
		return filePath;
	}
	
	/**
	 * 上传文件保存文件方法
	 * <br>解决跨域上传图片 将图片先写入本地文件,再从本地上传至图片服务器
	 * <br>缺点：图片经过两次读写及服务器之间的传输,效率较慢
	 * @author xingyuan
	 * @date 2016-3-21
	 * <!------------------>
	 * @param items 请求的上传图片的信息 
	 * <br>ServletFileUpload upload = new ServletFileUpload(factory);
	 * <br>List items = upload.parseRequest(request);
	 * @param path 保存本地的路径
	 * @return
	 * @throws Exception String
	 */
	public static String uploadFile(List<Object> items,String path) throws Exception{
		return uploadFile(items, path, "");
	}

}
