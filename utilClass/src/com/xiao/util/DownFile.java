package com.xiao.util;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 下载功能类
 * <p>Title: DownFile.java</p>
 * @author xxy 
 * @date 2014-6-18 上午8:46:53 
 * @version V1.0
 */
public class DownFile {
	
	/**
	 * 获取文件的名字
	 * 修改者名字   xxy
	 * 修改日期   2014-6-18
	 * 修改内容
	 * @param @param path	文件所在地址
	 * @param @return
	 * @return String
	 */
	public static String getFileName(String path){
		return path.substring(path.lastIndexOf("/")+1);
	}
	
	/**
	 * 下载的方法
	 * 修改者名字   xxy
	 * 修改日期   2014-6-18
	 * 修改内容
	 * @param @param path	下载地址
	 * @param @param threadsize	线程数量
	 * @param @throws Exception
	 * @return void
	 */
	public static void download(String path,int threadsize) throws Exception{
		System.out.println("download begin!");
		URL url = new URL(path);
		//	获取HttpURLConnection对象
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");	//	设置请求格式为GET提交
		conn.setReadTimeout(5*1000);	//	设置超时时间
		int filelength = conn.getContentLength();	//	获取要下载文件的长度
		String filename = getFileName(path);
		File saveFile = new File(filename);
		RandomAccessFile accessFile = new RandomAccessFile(saveFile, "rwd");
		accessFile.setLength(filelength);
		accessFile.close();
		int block = filelength%threadsize == 0?filelength/threadsize:filelength/threadsize+1;
		for(int threadid = 0;threadid <= threadsize;threadid++){
			System.out.println("url:"+url+",saveFile:"+saveFile+",block:"+block+",threadid:"+threadid);
			new DownloadThread(url,saveFile,block,threadid);
		}
		System.out.println("download ok!");
	}
	private final static class DownloadThread extends Thread{
		private URL url;
		private File saveFile;
		private int block;	//	每条线程下载的长度
		private int threadid;	//	线程id
		
		public DownloadThread(URL url,File saveFile,int block,int threadid){
			this.url = url;
			this.saveFile = saveFile;
			this.block = block;
			this.threadid = threadid;
			run();
		}
		
		@Override
		public void run(){
			System.out.println("run begin!");
			//	计算开始位置的公式：线程ID*每条线程下的数据长度
			//	计算结束位置的公式：（线程ID+1）*每条线程下载数据长度-1=？
			int startposition = threadid*block;
			int endposition = (threadid+1)*block-1;
			System.out.println("开始位置："+startposition);
			System.out.println("结束位置："+endposition);
			try{
				RandomAccessFile accessFile = new RandomAccessFile(saveFile, "rwd");
				accessFile.seek(startposition);	//	设置从什么位置写入数据
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setReadTimeout(5*1000);
				conn.setRequestProperty("Range", "bytes= "+startposition+"-"+endposition);
				InputStream inStream = conn.getInputStream();
				byte[]buffer = new byte[1024];
				int len = 0;
				while((len = inStream.read(buffer)) != -1){
					accessFile.write(buffer,0,len);
				}
				inStream.close();
				accessFile.close();
				System.out.println("线程id："+threadid+"下载完成!");
			}catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("run ok!");
		}
	}
	
}
