package com.xiao.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * 图片转换类
 * <p>Title: ImgTransformation.java</p>
 * @author xxy 
 * @date 2014-9-27 下午4:36:18 
 * @version V1.0
 */
public class ImgTransformation {
	
	/**
	 * 将图片转换成二进制
	 * 修改者名字   xxy
	 * 修改日期   2014-4-24
	 * 修改内容
	 * @param @param name	图片的名字(全路径)
	 * @param @return
	 * @param @throws Exception
	 * @return byte[]
	 */
	public static byte[] getImageBinary(String name) throws Exception{
		
		File f = new File(name);
		if(f.length()<0){
			System.out.println("no picture");
			return null;
		}else{
			BufferedImage bi = null;
			bi = ImageIO.read(f);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", baos);
			byte[] bytes = baos.toByteArray();
			System.out.println(bytes);
			return bytes;
		}
	}
	/**
	 * 
	 * 将通过上传得到的图片(路径)转换成二进制
	 * 修改者名字   xxy
	 * 修改日期   2014-4-24
	 * 修改内容
	 * @param @param name	图片的名字(全路径)
	 * @param @return
	 * @param @throws Exception
	 * @return byte[]
	 */
	public static byte[] getFileImgeBinary(String name) throws Exception{
		
		FileInputStream f = new FileInputStream(name);
		if(f == null || "".equals(f)){
			System.out.println("no picture");
			return null;
		}else{
			BufferedImage bi = null;
			bi = ImageIO.read(f);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", baos);
			byte[] bytes = baos.toByteArray();
			System.out.println(bytes);
			return bytes;
		}
	}
	
	/**
	 * 
	 * 将二进制转换成图片，并输出到指定目录
	 * 修改者名字   xxy
	 * 修改日期   2014-4-24
	 * 修改内容
	 * @param @param bytes	二进制图片
	 * @param @param imgURL	输出图片的目录
	 * @return void
	 */
	public static void saveImage(byte[] bytes,String imgURL){
		try{
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			BufferedImage bi1 = ImageIO.read(bais);
			
			File f = new File(imgURL);
			ImageIO.write(bi1, "jpg", f);
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * 将图片转化为图片流
	 * 修改者名字   xxy
	 * 修改日期   2014-10-6
	 * 修改内容
	 * @param @param imgPath	图片路径
	 * @param @return
	 * @return InputStream
	 */
	public static InputStream getInputStream(String imgPath){
		InputStream input = null;
		try {
			input = new FileInputStream(imgPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return input;
	}
	
}
