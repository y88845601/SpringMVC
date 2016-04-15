package com.xiao.util;


/**
 * 加、解密公共类
 * <p>Title: PtionData.java</p>
 * @author xxy 
 * @date 2014-5-29 上午9:31:36 
 * @version V1.0
 */
public class PtionData {
	//encryption 加密
	//decryption 解密
	
	/**
	 * 数据进行base64解码，返回String型
	 * 修改者名字   xxy
	 * 修改日期   2014-5-29
	 * 修改内容
	 * @param @param s
	 * @param @return
	 * @return String
	 */
	public static String getstrFromBASE64(String s) {
		if (s == null)
			return null;
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 数据进行base64解码，返回byte型
	 * 修改者名字   xxy
	 * 修改日期   2014-5-29
	 * 修改内容
	 * @param @param s
	 * @param @return
	 * @return byte[]
	 */
	public static byte[] getbyteFromBASE64(String s) {
		if (s == null)
			return null;
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		try {
			return decoder.decodeBuffer(s);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 数据进行base64编码
	 * 修改者名字   xxy
	 * 修改日期   2014-5-29
	 * 修改内容
	 * @param @param s
	 * @param @return
	 * @return String
	 */
	public static String getrevFromBASE64(byte[] s) {
		if (s == null)
			return null;
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		try {
			return encoder.encode(s);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 得到一串字符的ASCII码
	 * 修改者名字   xxy
	 * 修改日期   2014-6-25
	 * 修改内容
	 * @param @param bySourceByte
	 * @param @return
	 * @return String
	 */
	public static String getASCII(byte []bySourceByte)
	{
		int len,i;
		byte tb;
		char high,tmp,low;
		String result=new String();
		len=bySourceByte.length;
		for(i=0;i<len;i++)
		{
			tb=bySourceByte[i];
			
			tmp=(char)((tb>>>4)&0x000f);
			if(tmp>=10)
				high=(char)('a'+tmp-10);
			else
				high=(char)('0'+tmp);
			result+=high;
			tmp=(char)(tb&0x000f);
			if(tmp>=10)
				low=(char)('a'+tmp-10);
			else
				low=(char)('0'+tmp);
			
			result+=low;
		}
		return result;
	}
		/**
		 * java实现javascript中的escape编码函数,与unescape解码函数相对应
		 * 修改者名字   xxy
		 * 修改日期   2014-7-1
		 * 修改内容
		 * @param @param src
		 * @param @return
		 * @return String
		 */
	    public static String escape(String src) {
	        int i;
	        char j;
	        StringBuffer tmp = new StringBuffer();
	        tmp.ensureCapacity(src.length() * 6);
	        for (i = 0; i < src.length(); i++) {
	            j = src.charAt(i);
	            if (Character.isDigit(j) || Character.isLowerCase(j)
	                    || Character.isUpperCase(j))
	                tmp.append(j);
	            else if (j < 256) {
	                tmp.append("%");
	                if (j < 16)
	                    tmp.append("0");
	                tmp.append(Integer.toString(j, 16));
	            } else {
	                tmp.append("%u");
	                tmp.append(Integer.toString(j, 16));
	            }
	        }
	        return tmp.toString();
	    }
	    /**
	     * java实现javascript中的unescape解码函数,与escape编码函数相对应
	     * 修改者名字   xxy
	     * 修改日期   2014-7-1
	     * 修改内容
	     * @param @param src
	     * @param @return
	     * @return String
	     */
	    public static String unescape(String src) {
	        StringBuffer tmp = new StringBuffer();
	        tmp.ensureCapacity(src.length());
	        int lastPos = 0, pos = 0;
	        char ch;
	        while (lastPos < src.length()) {
	            pos = src.indexOf("%", lastPos);
	            if (pos == lastPos) {
	                if (src.charAt(pos + 1) == 'u') {
	                    ch = (char) Integer.parseInt(
	                            src.substring(pos + 2, pos + 6), 16);
	                    tmp.append(ch);
	                    lastPos = pos + 6;
	                } else {
	                    ch = (char) Integer.parseInt(
	                            src.substring(pos + 1, pos + 3), 16);
	                    tmp.append(ch);
	                    lastPos = pos + 3;
	                }
	            } else {
	                if (pos == -1) {
	                    tmp.append(src.substring(lastPos));
	                    lastPos = src.length();
	                } else {
	                    tmp.append(src.substring(lastPos, pos));
	                    lastPos = pos;
	                }
	            }
	        }
	        return tmp.toString();
	    }
	
}

