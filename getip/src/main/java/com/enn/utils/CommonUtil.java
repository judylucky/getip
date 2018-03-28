package com.enn.utils;

import java.util.ArrayList;
import java.util.List;

public class CommonUtil {
	/**
	 * 提取文件的类型
	 * 
	 * @param path
	 * @return
	 */
	public static String getPostfix(String path) {
		String filesuffix ="";
		if(path.contains(ConstantUtils.SUFFIX)){
			 filesuffix = path.substring(path.lastIndexOf(ConstantUtils.SUFFIX) + 1, path.length());
		}
		return filesuffix;
	}
	/**
	 * 根据个数创建变量名
	 */
	public static List<String> paramCreate(int num){
		List<String> paramsList = new ArrayList<>();
		for (int i = 0; i < num+1; i++) {
			paramsList.add("filed"+(i+1));
		}
		return paramsList;
		
	}
}
