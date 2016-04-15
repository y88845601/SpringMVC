package com.xiao.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

import com.swetake.util.Qrcode;

/**
 * 二维码编码、解码
 * <p>Title: TwoDimensionCode.java</p>
 * @author xxy 
 * @date 2014-10-6 上午10:47:22 
 * @version V1.0
 */
public class TwoDimensionCode {
	
	private String ccbPath = "D:\\bank\\img\\QRCodeImg.png";

	/**
	 * 生成二维码QRCode图片
	 * 修改者名字   xxy
	 * 修改日期   2014-10-6
	 * 修改内容
	 * @param @param content 存储内容
	 * @param @param imgPath	图片路径(带格式)
	 * @return void
	 */
	public void encoderQRCode(String content,String imgPath){
		this.encoderQRCode(content, imgPath, "png", 7);
	}
	
	/**
	 * 生成二维码QRCode图片
	 * 修改者名字   xxy
	 * 修改日期   2014-10-6
	 * 修改内容
	 * @param @param content	存储内容
	 * @param @param output	输出流
	 * @return void
	 */
	public void encoderQRCode(String content,OutputStream output ){
		this.encoderQRCode(content, output, "png", 7);
	}
	
	/**
	 * 生成二维码QRCode图片
	 * 修改者名字   xxy
	 * 修改日期   2014-10-6
	 * 修改内容
	 * @param @param content	存储内容
	 * @param @param imgPath	图片存储路径(带格式)
	 * @param @param imgType	图片类型
	 * @return void
	 */
	public void encoderQRCode(String content,String imgPath,String imgType){
		this.encoderQRCode(content, imgPath, imgType, 7);
	}
	
	/**
	 * 生成二维码QRCode图片
	 * 修改者名字   xxy
	 * 修改日期   2014-10-6
	 * 修改内容
	 * @param @param content	存储内容
	 * @param @param output	输出流
	 * @param @param imgType	图片类型
	 * @return void
	 */
	public void encoderQRCode(String content,OutputStream output,String imgType){
		this.encoderQRCode(content, output, imgType, 7);
	}
	
	/**
	 * 生成二维码QRCode图片
	 * 修改者名字   xxy
	 * 修改日期   2014-10-6
	 * 修改内容
	 * @param @param content	存储内容
	 * @param @param output	输出流
	 * @param @param imgType	图片类型
	 * @param @param size	二维码尺寸
	 * @return void
	 */
	public void encoderQRCode(String content,OutputStream output,String imgType,int size){
		BufferedImage bufImg = this.qRCodeCommon(content, size,ccbPath);
		try {
			//	生成二维码QRCode图片
			ImageIO.write(bufImg, imgType, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成二维码QRCode图片
	 * 修改者名字   xxy
	 * 修改日期   2014-10-6
	 * 修改内容
	 * @param @param content	储存内容
	 * @param @param imgPath	图片路径(带格式)
	 * @param @param imgType	图片类型
	 * @param @param size	二维码尺寸
	 * @return void
	 */
	public void encoderQRCode(String content,String imgPath,String imgType,int size){
		BufferedImage bufImg = this.qRCodeCommon(content, size,ccbPath);
		File imgFile = new File(imgPath);
		try {
			//	生成二维码QRCode图片
			ImageIO.write(bufImg, imgType, imgFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 生成二维码(QRCode)图片的公共方法
	 * 修改者名字   xxy
	 * 修改日期   2014-10-6
	 * 修改内容
	 * @param @param content	储存内容
	 * @param @param size	二维码尺寸
	 * @param @param ccbPath	二维码图片中间的logo路径
	 * @param @return
	 * @return BufferedImage
	 */
	private BufferedImage qRCodeCommon(String content,int size,String ccbPath){
		
		BufferedImage bufImg = null;
		
		try{
			Qrcode qrcodeHeandler = new Qrcode();
			// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小，纠错级别越高，纠错信息占用的空间越多，那么能存储的有用讯息就越少；
			qrcodeHeandler.setQrcodeErrorCorrect('M');
			//	设置编码方式 B-表示一种编码方式
			qrcodeHeandler.setQrcodeEncodeMode('B');
			//	设置二维码的尺寸,也象征着二维码的信息容量；二维码可以看成一个黑白方格矩阵，版本不同，矩阵长宽方向方格的总数量分别不同，
			//	版本1为21*21矩阵，版本每增1，二维码的两个边长都增4；所以版本7为45*45的矩阵；最高版本为是40，是177*177的矩阵；
			qrcodeHeandler.setQrcodeVersion(size);
			//	设置内容的字节数组，设置编码格式
			byte[] contentBytes = content.getBytes("utf-8");
			//	设置图片尺寸
			int imgSize = 67 + 12 *(size - 1);
			bufImg = new BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
			//	设置背景颜色
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imgSize, imgSize);
			//	设定图像颜色>BLACK
			gs.setColor(Color.BLACK);
			//	设置偏移量，不设置可能导致解析出错
			int pixoff = 2;
			//	输出内容> 二维码
			if(contentBytes.length > 0 && contentBytes.length < 800){
				boolean[][] codeOut = qrcodeHeandler.calQrcode(contentBytes);
				for(int i = 0;i < codeOut.length; i++){
					for(int j = 0;j < codeOut.length; j++){
						if(codeOut[j][i]){
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			}else{
				throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0,800].");
			}
			Image img = ImageIO.read(new File(ccbPath)).getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING);	//	参数说明 width、height、缩放方式
			gs.drawImage(img, 55,55,null);
			gs.dispose();
			bufImg.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return bufImg;
	}
	
	/**
	 * 解析二维码
	 * 修改者名字   xxy
	 * 修改日期   2014-10-6
	 * 修改内容
	 * @param @param input	输入流
	 * @param @return
	 * @return String
	 */
	public String decoderQRCode(InputStream input){
		BufferedImage bufImg = null;
		String content = null;
		
		try {
			bufImg = ImageIO.read(input);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}catch (DecodingFailedException dfe) {
			dfe.printStackTrace();
		}
		return content;
	}
	
	/**
	 * 解析二维码
	 * 修改者名字   xxy
	 * 修改日期   2014-10-6
	 * 修改内容
	 * @param @param imgPath	图片路径
	 * @param @return
	 * @return String
	 */
	public String decoderQRCode(String imgPath){
		File imageFile = new File(imgPath);
		BufferedImage bufImg = null;
		String content = null;
		
		try {
			bufImg = ImageIO.read(imageFile);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)),"utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		} catch(DecodingFailedException dfe){
			dfe.printStackTrace();
		}
		
		return content;
	}
	
}


/**
 * 二维码图片对象类，辅助解析二维码
 * <p>Title: TwoDimensionCode.java</p>
 * @author xxy 
 * @date 2014-10-6 下午4:48:03 
 * @version V1.0
 */
class TwoDimensionCodeImage implements QRCodeImage{

	BufferedImage bufImg;
	
	public TwoDimensionCodeImage(BufferedImage bufImg){
		this.bufImg = bufImg;
	}
	
	public int getHeight() {
		return bufImg.getHeight();
	}

	public int getPixel(int x, int y) {
		return bufImg.getRGB(x, y);
	}

	public int getWidth() {
		return bufImg.getWidth();
	}
	
}


























