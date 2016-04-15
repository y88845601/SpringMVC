package com.xiao.util;

/**
 * 排序方法类
 * <p>Title: SortIng.java</p>
 * @author xxy 
 * @date 2015-6-10 上午10:58:15 
 * @version V1.0
 */
public class SortIng {
	
	/**
	 * 将字符串数组按照ASCII码的字典序排列
	 * 修改者名字   xxy
	 * 修改日期   2015-6-10
	 * 修改内容
	 * @param @param str
	 * @param @return
	 * @return String[]
	 */
	public String[] getASCII(String[] str){
		int i0 = str.length;//	数组的长度
		int i1 = str[0].length();//	获取最长字符串的长度
		for(int i = 0;i < str.length;i++){
			if(i+1 != str.length){
				if(str[i].length() < str[i+1].length()){
					i1 = str[i+1].length();
				}
			};
		}
		for(int i = 1;i < i1;i++){
			for(int j = 0;j < i0;j++){
				String a1 = null;
				if(str[j].length() > i){
					a1 = str[j].subSequence(i-1, i).toString();
				}
				String a2 = null;
				if(j+1<str.length){
					if(str[j+1].length() > i){
						a2 = str[j+1].subSequence(i-1, i).toString();
					}
				}
				String str2 = null;
				if(a1 != null && a2 != null){
					if((int)a1.toCharArray()[0] > (int)a2.toCharArray()[0]){
						if(str[j].subSequence(0, i-1).equals(str[j+1].subSequence(0, i-1)) || i == 1){
							str2 = str[j];
							str[j] = str[j+1];
							str[j+1] = str2;
							getASCII(str);
						}
					}
				}
			}
		}
		return str;
	}

}
