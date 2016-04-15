package com.xiao.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;


/**
 * 在图片上添加文字
 * <p>Title: DrawStringInImg.java</p>
 * @author xxy 
 * @date 2015-1-9 上午10:16:10 
 * @version V1.0
 */
public class DrawStringInImg {
	private static Font mFont = new Font("宋体",Font.PLAIN,20);
	
	public static void main(String[] args) {

        File f1 = new File("F:\\1.txt");
        File f2 = new File("F:\\test2.jpg");
        File f3 = new File("F:\\test.jpg");
        draw2(f1,f2,f3,false);
        
    }
	
	public static void draw2(File content,File outPictrue,File background,boolean b){
		FileReader f = null;
		try {
			f = new FileReader(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(f);
		String line = "";
		
		int lineNum = 0; //	一共多少行
		List<String> list = new ArrayList<String>();
		try {
			while((line = br.readLine()) != null){
				System.out.println(line);
				list.add(line);
				++lineNum;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		int[] iArray = new int[list.size()];
		int length = list.size();
        for(int k = 0 ; k < length ; k++){
            iArray[k] = format1(list.get(k));
        }
        Arrays.sort(iArray);
        int lineLength = iArray[length-1];
        System.out.println("文本中最长的一行的长度是："+lineLength);
        System.out.println("一共的行数是："+lineNum);
        BufferedImage image = null;
        if( background.equals("") || background == null){
            double h = 256/16;
            double w = 85/10;
            int width=(int) (w*lineLength)+ 40 , height=(int) (h*lineNum);
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        }else{
            try {
                image = ImageIO.read(background);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        Graphics g = image.getGraphics();
//        g.setColor(getRandColor(200,250));
//        g.fillRect(1, 1, width-1, height-1);
        if( b == false){
        g.setColor(new Color(102,102,102));  //设字体为黑色,否则就是白色
        }
//        g.drawRect(0, 0, width-1, height-1);
        g.setFont(mFont);
        int k = 0;
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            g.drawString(it.next(), 30, 15 + 20*k);
            ++k;
        }
        try {
            ImageIO.write(image, "JPEG", outPictrue);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	//返回占位符的长度
    public static int format1( String s){
           int length = 0 ;
           for(int t = 0; t < s.length() ; t++){
               if( s.charAt(t)> 255){
                   length = length + 2;
               }else{
                   length = length + 1 ;
               }
           }
           System.out.println(length);
           return length;
    }

}
